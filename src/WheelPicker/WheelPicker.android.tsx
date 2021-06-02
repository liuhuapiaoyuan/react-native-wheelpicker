import { requireNativeComponent} from 'react-native'
import type {PickerProps,PickerItemProps} from '@react-native-picker/picker';
import React from 'react';

export interface RCTWheelPickerProps {
  data:any
  selectedIndex?:number 
  itemSpace?:number 
  textColor?:string 
  textSize?:number 
  onValueChange?:any

}
const RCTWheelPicker = requireNativeComponent<RCTWheelPickerProps>('RCTWheelPicker')
function  stateFromProps<T>(props:PickerProps<T>){
  let selectedIndex = 0;
  const items:any[] = [];
  //@ts-ignore
  React.Children.forEach(props.children, function (child, index) {
    if (child.props.value === props.selectedValue) {
      selectedIndex = index;
    }
    items.push({value: child.props.value, label: child.props.label});
  });

  return {selectedIndex, items};
}


export default function WheelPicker<T=number>(props:PickerProps<T>){
  const {selectedIndex , items} = stateFromProps<T>(props)
  const { onValueChange , ...rest } = props
  return <RCTWheelPicker
  selectedIndex={selectedIndex}
  data={items}
  //@ts-ignore
  textColor={props?.itemStyle?.color}
  //@ts-ignore
  textSize={props?.itemStyle?.fontSize}
  onValueChange={(e:any)=>{
    //@ts-ignore
    onValueChange && onValueChange(items[e.nativeEvent.data]?.value)
  }}
  {...rest}
	  />
}


//@ts-ignore
function Item(props:PickerItemProps){
  return null
}

WheelPicker.Item = Item;