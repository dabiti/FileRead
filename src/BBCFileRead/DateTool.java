package BBCFileRead;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateTool {
	
	//当天日期 YYYY-MM-DD
	public static  String getNowDayForYYYMMDD(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatDate.format(new Date()); 
		return date;
	}
	//当天日期 YYYY-MM-DD
	public static  String getThreeMonthAgoYYYMMDD(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		 Calendar EndDate = Calendar.getInstance();
	        String dataStirng_ = null;
			try {
				EndDate.setTime(new Date());
	            EndDate.add(Calendar.MONTH, 3);
	            dataStirng_ =  formatDate.format(EndDate.getTime());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return dataStirng_;
		
		//String date = formatDate.format(new Date()); 
		//return date;
	}
	
	//按照格式获取格式化的当天日期
	public static  String getNowDayForYYYMMDD(String format){
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		String date = formatDate.format(new  Date()); 
		return date;
	}

	//上一天日期
	public static  String shangDay(String dateString){
	    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar EndDate = Calendar.getInstance();
        String dataStirng_ = null;
		try {
			EndDate.setTime(formatDate.parse(dateString));
            EndDate.add(Calendar.DAY_OF_MONTH, -1);
            dataStirng_ =  formatDate.format(EndDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return dataStirng_;
	}
	
	//下一天日期
	public static  String xiaDay(String dateString,String format){
	    SimpleDateFormat formatDate = new SimpleDateFormat(format);
        Calendar EndDate = Calendar.getInstance();
        String dataStirng_ = null;
		try {
			EndDate.setTime(formatDate.parse(dateString));
            EndDate.add(Calendar.DAY_OF_MONTH, 1);
            dataStirng_ =  formatDate.format(EndDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return dataStirng_;
	}
	
	//当月天数 YYYY-MM-DD
	public static  int month(String dateString){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(formatDate.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)-1);
		return Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}
	
	
	//日期比较
	public static boolean bijRQ(String beginDate,String endDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar s1 = Calendar.getInstance();
		Calendar s2 = Calendar.getInstance();
		try {
			s1.setTime(format.parse(beginDate));
			s2.setTime(format.parse(endDate));
			if(s1.after(s2))
			{
				return true;
			}
		} catch (ParseException e) {
			return true;
		}
		return false;
	}
	
	//获取指定格式日期
	public static String getDataTime(String DataFormat) {
		Date operateDate = new java.util.Date();
		SimpleDateFormat operateDateFormat = new java.text.SimpleDateFormat(DataFormat);
		return operateDateFormat.format(operateDate).toLowerCase();
	}
	
	//时间格式转换 输出: YYYYmm 年份YYYY 月份mm 例如:201101
	public static String toYYYYMM(){
		 Calendar DBDate = Calendar.getInstance();
		 DBDate.set(Calendar.DAY_OF_MONTH, -1);
		 String year=String.valueOf(DBDate.get(DBDate.YEAR));
		 String month =String.valueOf(DBDate.get(DBDate.MONTH)+1);
		 return year+(month.length()==1?("0"+month):month);
	}
	
	//日期字符串 转换为DATE---江西
	public static Date strToDate(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
		Date date = null;    
		try {    
		    date = format.parse(dateString); 
		} catch (ParseException e) {    
		    e.printStackTrace();    
		}
		return date;
	}

	//将yyyymmdd格式转换成yyyy-mm-dd格式
	
	public static String toSimpleFormat(String yyyyMMdd){
		if(yyyyMMdd==null||yyyyMMdd.trim().length()<8){
			return yyyyMMdd;
		}
		yyyyMMdd=yyyyMMdd.substring(0,4)+"-"+yyyyMMdd.substring(4,6)+"-"+yyyyMMdd.substring(6);
		return yyyyMMdd;
	}

	//将yyyy/mm/dd格式转换成yyyy-mm-dd格式
	
	public static String toSimpleFormatByddmmyyyy(String ddMMYYYY){
		if(ddMMYYYY==null||ddMMYYYY.trim().length()<8){
			return ddMMYYYY;
		}
		ddMMYYYY=ddMMYYYY.substring(6)+"-"+ddMMYYYY.substring(3,5)+"-"+ddMMYYYY.substring(0,2);
		return ddMMYYYY;
	}
	
	//将yyyymmdd格式转换成yyyy-mm-dd格式
	public static String toSimpleFormatToddmmyyyy(String datestr){
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");  
		DateFormat format = null;
		if(datestr.length()>10){
		format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		}else{
			format=new SimpleDateFormat("yyyy-MM-dd");     	
		}
		Date date = null;    
		String dateString="";
		try {    
		    date = format.parse(datestr); 
		    dateString=format1.format(date);
		} catch (ParseException e) {    
		    e.printStackTrace();    
		}
		return dateString;
	}
	
	//yyyy-mm-dd格式转换成yyyy/mm/dd格式
	
	public static String toSimpleFormat2(String date){
		if(date==null||date.trim().length()<8){
			return date;
		}
		date=date.substring(0,4)+"/"+date.substring(5,7)+"/"+date.substring(8,10);
		return date;
	}
	

}
