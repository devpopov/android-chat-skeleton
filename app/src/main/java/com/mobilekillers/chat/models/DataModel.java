package com.mobilekillers.chat.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sergey on 18.02.16.
 */
@SuppressWarnings("serial")
public class DataModel implements Serializable {
    public DataModel() {

    }

    public DataModel(Map<String, Object> data) {
        set(data);
    }

    public Map<String, Object> get() {
        Map<String,Object> data = new HashMap<String, Object>();

        Field[] fields = this.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            Field field = fields[j];
            if(!Modifier.isPrivate(field.getModifiers())) {
                String fieldName = field.getName().toLowerCase();
                try {
                    Object object = field.get(this);
                    if (object != null)
                        data.put(fieldName, object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    public void set(Map<String, Object> data) {
        for (Iterator<String> i = data.keySet().iterator(); i.hasNext(); ) {
            String key = i.next();
            Field[] fields = this.getClass().getDeclaredFields();

            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                if(!Modifier.isPrivate(field.getModifiers())) {
                    String fieldName = field.getName().toLowerCase();
                    if (fieldName.contentEquals(key)) {
                        try {
                            field.set(this, data.get(key));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}