package com.zbwfisher.datasource.connect;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zbw on 17/5/4.
 */
public class ToolUtil {
    private static final Logger LOGGER = Logger.getLogger(ToolUtil.class);

    public static Object requestToBean(HttpServletRequest request, Object bean, boolean processNull) {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getModifiers() == 1)
                fillField(request.getParameterMap(), method, bean, processNull);
        }
        return bean;
    }

    public static Object mapToBean(Map map, Object bean, boolean processNull, boolean processLine) {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getModifiers() == 1)
                fillField(map, method, bean, processNull, processLine);
        }
        return bean;
    }

    public static Object mapToBean(Map map, Object bean, boolean processNull) {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getModifiers() == 1)
                fillField(map, method, bean, processNull);
        }
        return bean;
    }

    private static void fillField(Map map, Method method, Object bean, boolean processNull) {
        fillField(map, method, bean, processNull, false);
    }

    private static void fillField(Map map, Method method, Object bean, boolean processNull, boolean processLine) {
        String methodName = method.getName();
        if (methodName.startsWith("set")) {
            String fName = methodName.substring(3);
            Object valueObj = null;
            Iterator keyIte = map.keySet().iterator();
            while (keyIte.hasNext()) {
                Object key = keyIte.next();
                if (!processLine) {
                    if (((key instanceof String)) && (fName.equalsIgnoreCase((String) key))) {
                        valueObj = map.get(key);
                    }
                } else if (((key instanceof String)) && (fName.equalsIgnoreCase(((String) key).replaceAll("\\_", "")))) {
                    valueObj = map.get(key);
                }

            }

            boolean porcess = true;
            if ((valueObj == null) && (!processNull)) {
                porcess = false;
            }
            if (porcess)
                setMethodValue(bean, method, valueObj, processNull);
        }
    }

    private static void setMethodValue(Object obj, Method method, Object valueObj, boolean processNull) {
        try {
            boolean update = true;
            Object setValue = null;
            if (valueObj != null) {
                Class cls = method.getParameterTypes()[0];
                if (cls.equals(valueObj.getClass())) {
                    setValue = valueObj;
                } else {
                    String valueStr = objectToString(valueObj);
                    if (!valueStr.trim().equals("")) {
                        if (cls == String.class) {
                            setValue = valueStr;
                        } else if (cls == Integer.class) {
                            try {
                                setValue = Integer.valueOf(valueStr);
                            } catch (NumberFormatException e) {
                                setValue = null;
                            }
                        } else if (cls == Float.class) {
                            try {
                                setValue = Float.valueOf(valueStr);
                            } catch (NumberFormatException e) {
                                setValue = null;
                            }
                        } else if (cls == BigDecimal.class) {
                            try {
                                setValue = new BigDecimal(valueStr);
                            } catch (NumberFormatException e) {
                                setValue = null;
                            }
                        } else if (cls == java.sql.Date.class) {
                            setValue = new java.sql.Date(DateUtil.parse(valueStr).getTime());
                        } else if (cls == java.util.Date.class) {
                            setValue = new java.util.Date(DateUtil.parse(valueStr).getTime());
                        } else if (cls == Timestamp.class) {
                            setValue = DateUtil.parseTimestamp(valueStr);
                        } else if (cls == Long.class) {
                            try {
                                setValue = Long.valueOf(valueStr);
                            } catch (NumberFormatException e) {
                                setValue = null;
                            }
                        } else if (cls == Double.class) {
                            try {
                                setValue = Double.valueOf(valueStr);
                            } catch (NumberFormatException e) {
                                setValue = null;
                            }
                        } else if (cls == Map.class) {
                            setValue = valueObj;
                        } else {
                            update = false;
                            LOGGER.error("Value type '" + cls.getName() + "' is not supported!");
                        }
                    } else setValue = null;
                }
            }

            if ((update) && (
                    (valueObj != null) || (processNull))) {
                method.invoke(obj, new Object[]{setValue});
            }
        } catch (Exception e) {
            LOGGER.error("Error occured when invoke method '" + method.getName() + "' of '" + obj.getClass().getName() +
                    "'", e);
        }
    }

    private static String objectToString(Object valueObj) {
        if ((valueObj instanceof String[])) {
            String[] array = (String[]) valueObj;
            if (array.length == 0) {
                return "";
            }

            return array[0];
        }

        return valueObj.toString();
    }

    public static HashMap beanToMap(Object beanObj, HashMap map, boolean processNull) {
        return beanToMap(beanObj, map, processNull, "yyyy-MM-dd", -1);
    }

    public static HashMap beanToMap(Object beanObj, HashMap map, boolean processNull, String datePattern) {
        return beanToMap(beanObj, map, processNull, datePattern, -1);
    }

    public static HashMap beanToMap(Object beanObj, HashMap map, boolean processNull, int scale) {
        return beanToMap(beanObj, map, processNull, "yyyy-MM-dd", scale);
    }

    public static HashMap beanToMap(Object beanObj, HashMap map, boolean processNull, String datePattern, int scale) {
        HashMap result = map;
        Method[] methods = beanObj.getClass().getDeclaredMethods();

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getModifiers() == 1)
                fillFieldToMap(method, beanObj, processNull, datePattern, scale, result);
        }
        return result;
    }

    private static void fillFieldToMap(Method m, Object obj, boolean processNull, String datePattern, int scale, HashMap result) {
        String methodName = m.getName();
        try {
            if ((methodName.startsWith("get")) && (methodName.length() > 3) && (m.getParameterTypes().length == 0)) {
                String key = String.valueOf(Character.toLowerCase(methodName.charAt(3)));
                if (methodName.length() > 4)
                    key = key + methodName.substring(4);
                Object valueObj = m.invoke(obj, new Object[0]);
                if (methodName.equals("getCompositeId")) {
                    if (valueObj != null) {
                        Method[] pkMethods = valueObj.getClass().getDeclaredMethods();

                        for (int j = 0; j < pkMethods.length; j++) {
                            Method pkMethod = pkMethods[j];
                            if (pkMethod.getModifiers() == 1)
                                fillFieldToMap(pkMethod, valueObj, processNull, datePattern, scale, result);
                        }
                    }
                } else if (valueObj != null) {
                    String value = null;
                    if ((valueObj instanceof java.sql.Date)) {
                        value = DateUtil.format((java.sql.Date) valueObj, datePattern);
                    } else if ((valueObj instanceof Timestamp)) {
                        value = DateUtil.format((Timestamp) valueObj, datePattern);
                    } else if (((valueObj instanceof BigDecimal)) && (scale > 0)) {
                        BigDecimal bd = (BigDecimal) valueObj;
                        bd = bd.setScale(scale, 4);
                        value = bd.toString();
                    } else {
                        value = valueObj.toString();
                    }

                    if ((processNull) || (value != null))
                        result.put(key, value);
                } else if (processNull) {
                    result.put(key, null);
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
