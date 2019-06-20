package ru.javawebinar.topjava.util;

public class ParserUtil {
    public static int getInt(String value){
        try{
            return Integer.parseInt(value);
        } catch (Exception e){
            return 0;
        }
    }

    public static int getMonthOrDay(String value){
        try{
            return Integer.parseInt(value);
        } catch (Exception e){
            return 1;
        }
    }

    public static int getYear(String value){
        try{
            return Integer.parseInt(value);
        } catch (Exception e){
            return 2000;
        }
    }
}
