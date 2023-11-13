package com.study.about;

import com.google.common.base.CaseFormat;

import java.util.HashMap;

public class MyCamelHashMap extends HashMap {
    @Override
    public Object put(Object key, Object value) {
        String myKey = (String) key;

        // UPPER_UNDERSCORE : p_no ==> P_NO
        // LOWER_CAMEL  : p_No

        return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, myKey), value);
    }
}
