package com.zyu;

import android.graphics.Color;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author <a href="mailto:lesliesam@hotmail.com"> Sam Yu </a>
 */
public class ReactWheelCurvedPickerManager extends SimpleViewManager<ReactWheelCurvedPicker> {

    private static final String REACT_CLASS = "RCTWheelPicker";

    private static final int DEFAULT_TEXT_SIZE = 25 * 2;
    private static final int DEFAULT_ITEM_SPACE = 14 * 2;

    @Override
    protected ReactWheelCurvedPicker createViewInstance(ThemedReactContext reactContext) {
        ReactWheelCurvedPicker picker = new ReactWheelCurvedPicker(reactContext);
        picker.setItemTextColor(Color.LTGRAY);
        picker.setSelectedItemTextColor(Color.BLACK);
        picker.setItemSpace(DEFAULT_ITEM_SPACE);
        //默认是蜷曲
        picker.setCurved(true);
        picker.setItemTextSize(DEFAULT_TEXT_SIZE);

        //初始化的propes
        return picker;
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onValueChange")
        );
    }

    @ReactProp(name="data")
    public void setData(ReactWheelCurvedPicker picker, ReadableArray items) {
        if (picker != null) {
            ArrayList<Dynamic> valueData = new ArrayList<>();
            ArrayList<String> labelData = new ArrayList<>();
            for (int i = 0; i < items.size(); i ++) {
                ReadableMap itemMap = items.getMap(i);
                valueData.add(itemMap.getDynamic("value"));
                labelData.add(itemMap.getString("label"));
            }
            picker.setValueData(valueData);
            picker.setData(labelData);
        }
    }

    @ReactProp(name="selectedIndex")
    public void setSelectedIndex(ReactWheelCurvedPicker picker, int index) {
        picker.setSelectedItemPosition(index,false);

    }

    @ReactProp(name="textColor", customType = "Color")
    public void setTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setItemTextColor(color);
        }
    }

    @ReactProp(name="selectedTextColor", customType = "Color")
    public void setSelectedTextColor(ReactWheelCurvedPicker picker, Integer color) {
        if (picker != null) {
            picker.setSelectedItemTextColor(color);
        }
    }

    @ReactProp(name="textSize")
    public void setTextSize(ReactWheelCurvedPicker picker, int size) {
        if (picker != null) {
            picker.setItemTextSize((int) PixelUtil.toPixelFromDIP(size));
        }
    }
    @ReactProp(name="isCyclic")
    public void setTextSize(ReactWheelCurvedPicker picker, Boolean isCyclic ) {
        if (picker != null ) {
            picker.setCyclic(null!=isCyclic ? isCyclic : false);
        }
    }
    @ReactProp(name="isCurved")
    public void setCurved(ReactWheelCurvedPicker picker, Boolean isCurved ) {
        if (picker != null ) {
            picker.setCurved(null!=isCurved ? isCurved : false);
        }
    }

    @ReactProp(name="itemSpace")
    public void setItemSpace(ReactWheelCurvedPicker picker, int space) {
        if (picker != null) {
            picker.setItemSpace((int) PixelUtil.toPixelFromDIP(space));
        }
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
