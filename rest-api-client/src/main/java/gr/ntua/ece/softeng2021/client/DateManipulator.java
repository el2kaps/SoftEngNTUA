package gr.ntua.ece.softeng2021.client;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;

public class DateManipulator {
    String date;

    public DateManipulator(){}
    public DateManipulator(String date) {
        this.date = date;
    }

    public String PrintDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(sdf.parse(this.date));// all done
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            String yearS = String.valueOf(year);
            String monthS = String.valueOf(month + 1);
            String dayS = String.valueOf(day);

            String finaldate = (yearS + "-" + monthS + "-" + dayS);
            //System.out.println(finaldate);
            return finaldate;
        } catch (ParseException e) {
            return "Return_date_failed";
        }
    }

    public Integer getYear() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(sdf.parse(this.date));// all done
            int year = cal.get(Calendar.YEAR);
            return year;
        } catch (ParseException e) {
            return -1;
        }
    }

    public Integer getMonth() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(sdf.parse(this.date));// all done
            int month = cal.get(Calendar.MONTH) + 1;
            return month;
        } catch (ParseException e) {
            return -1;
        }
    }

    public Integer getDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            cal.setTime(sdf.parse(this.date));// all done
            int day = cal.get(Calendar.DAY_OF_MONTH);
            return day;
        } catch (ParseException e) {
            return -1;
        }
    }

    public String PrintInverseDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
          cal.setTime(sdf.parse(this.date));// all done
          int year = cal.get(Calendar.YEAR);
          int month = cal.get(Calendar.MONTH);
          int day = cal.get(Calendar.DAY_OF_MONTH);
          String yearS = String.valueOf(year);
          String monthS = String.valueOf(month + 1); // SEPT 9 OXI 09
          String dayS = String.valueOf(day);

          if(Integer.parseInt(monthS)<10){
            monthS ="0"+monthS;
          }
          if(Integer.parseInt(dayS)<10){
            dayS ="0"+dayS;
          }
          String finaldate = (yearS+monthS+dayS);

          //System.out.println(finaldate);
          return finaldate;
        } catch (ParseException e) {
          return "Return_date_failed";
        }
      }

/*
        public String PrintInverseDate() {
          Calendar cal = Calendar.getInstance();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          try {
            cal.setTime(sdf.parse(this.date));// all done
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            String yearS = String.valueOf(year);
            String monthS ="PUTSA";
            String dayS = "GEIA";
            if (month<10){
              String monthS = "0"+String.valueOf(month + 1);
            }else{
              String monthS = String.valueOf(month + 1);
            }
            if(day<10){
              String dayS = "0"+String.valueOf(day);
            }else{
              String dayS = String.valueOf(day);
            }

            String finaldate = (yearS+monthS+dayS);

            System.out.println(finaldate);
            return finaldate;
          } catch (ParseException e) {
            return "Return_date_failed";
          }
        }

        */
    }
