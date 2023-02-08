package com.blackboxgenerator;

import com.blackboxgenerator.helper.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Predicate;

public class PredicateBuilder {

//    private Predicate<Employee> predicate;
//    private boolean isError = false;

//    public PredicateBuilder(Map<String, String> filters ) {
//
//        buildPredicate(filters);
//    }
//
//    private  void buildPredicate(Map<String, String> filters) {
//
//        predicate = null;
//        for(String propertyName : filters.keySet()){
//
//            Method method = methodByName(convertPropertyNameToGetterName(propertyName));
//            if(method == null ) {
//                continue;
//            }
//            String value = filters.get(propertyName);
//            Predicate<Employee> p = makePredicate(method, value);
//            if(p == null ) {
//                continue;
//            }
//            predicate = predicate == null ? p : predicate.and(p);
//        }
//    }
//
//    private String convertPropertyNameToGetterName(String propertyName) {
//
//        propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
//        return "get" + propertyName;
//    }
//
//    private Predicate<Employee> makePredicate(Method method, String value){
//        isError = false;
//
//        Predicate<Employee> p = e -> {
//            try {
//
//                return method.invoke(e).equals(value);
//            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//
//                ex.printStackTrace();
//                isError = true;
//                return false;
//            }
//        };
//        return isError ? null : p;
//    }
//
//    private static Method methodByName(String name){
//        try {
//            return Employee.class.getDeclaredMethod(name);
//        } catch (NoSuchMethodException | SecurityException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

//    public Predicate<Employee> getPredicate() {
//        return predicate;
//    }

    static Predicate<Employee> buildPredicate(Map<String, String> filters) {
        Predicate<Employee> predicate = null;

        for (String key : filters.keySet()) {

            Predicate<Employee> p = null;
            switch (key) {
                case "firstName":
                    p = e -> e.getFirstName().equals(filters.get(key));
                    break;

                case "country":
                    p = e -> e.getCountry().equals(filters.get(key));
                    break;
            }
            if (p == null) {
                continue;
            }
            predicate = predicate == null ? p : predicate.and(p);
        }
        return predicate;
    }
}
