package BBCFileRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////
//							  _ooOoo_                             //
//							 o8888888o                            //    
//							 88" . "88                            //    
//							 (| ^_^ |)                            //    
//							 O\  =  /O                            //
//						  ____/`---'\____                         //                        
//						.'  \\|     |//  `.                       //
//					   /  \\|||  :  |||//  \                      //    
//					  /  _||||| -:- |||||-  \                     //
//					  |   | \\\  -  /// |   |                     //
//					  | \_|  ''\---/''  |   |                     //        
//					  \  .-\__  `-`  ___/-. /                     //        
//					___`. .'  /--.--\  `. . ___                   //    
// 				  ."" '<  `.___\_<|>_/___.'  >'"".                //
//				| | :  `- \`.;`\ _ /`;.`/ - ` : | |               //    
//				\  \ `-.   \_ __\ /__ _/   .-` /  /               //
//		  ========`-.____`-.___\_____/___.-`____.-'========       //    
//							   `=---='                            //
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//佛祖保佑       永无BUG        永不修改                    //
public class FileRead {

	public static void main(String[] args) {
		
		ReadFileThread testThread = new ReadFileThread("D:\\testccbdata\\20130707.txt");
		testThread.start();
	}


private static Connection getConn() {
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String username = "sinopec";
    String password = "sinopec";
    Connection conn = null;
    try {
        Class.forName(driver);
        // new oracle.jdbc.driver.OracleDriver();
        conn = DriverManager.getConnection(url, username, password);
    }
    catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    return conn;
}

private static int insert(String[] FiledGroup) {
    Connection conn = getConn();
    int result = 0;
    String sql = "insert into CCBBILLS (BILL_ID,TERMINAL_ID,CARD_ID,AMOUNT,POUNDAGE,SEAL_TYPE,CARD_TYPE,SEAL_DATE,SEAL_TIME) values(?,?,?,?,?,?,?,?,?)";
    PreparedStatement pstmt;
    String sDate = "";// 交易日期
    String sTime = "";// 交易时间
    String dateCollection; // 转换时间用临时
    float amount;// 交易金额
    float poundage;//手续费
    if(FiledGroup[1].length() == 10){
    	 dateCollection = DateTool.toSimpleFormat("2015"+FiledGroup[1]);
		 sDate = dateCollection.substring(0,10);
		 sTime = dateCollection.substring(10,12)+":"+dateCollection.substring(12,14)+":"+dateCollection.substring(14);
    }
    amount = Float.parseFloat(FiledGroup[4]);
    poundage = Float.parseFloat(FiledGroup[5]);
    try {
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, "empty");
        pstmt.setString(2, FiledGroup[0]);
        pstmt.setString(3, FiledGroup[2]);
        pstmt.setFloat(4, amount);
        pstmt.setFloat(5, poundage);
        pstmt.setString(6, FiledGroup[FiledGroup.length - 1]);
        pstmt.setString(7, "empty");
        pstmt.setString(8, sDate);
        pstmt.setString(9, sTime);
        
        result = pstmt.executeUpdate();
        System.out.println("resutl: " + result);

        pstmt.close();
        conn.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}

private void query() {
    Connection conn = getConn();
    String sql = "select * from users";
    PreparedStatement pstmt;
    try {
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("name: " + rs.getString("username")
                    + " \tpassword: " + rs.getString("password"));
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

}

private int update(String oldName, String newPass) {
    Connection conn = getConn();
    int i = 0;
    String sql = "update users set password='" + newPass
            + "' where username='" + oldName + "'";
    PreparedStatement pstmt;
    try {
        pstmt = conn.prepareStatement(sql);

        i = pstmt.executeUpdate();
        System.out.println("resutl: " + i);

        pstmt.close();
        conn.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    return i;
}

private int delete(String username) {
    Connection conn = getConn();
    int i = 0;
    String sql = "delete users where username='" + username + "'";
    PreparedStatement pstmt;
    try {
        pstmt = conn.prepareStatement(sql);

        i = pstmt.executeUpdate();
        System.out.println("resutl: " + i);

        pstmt.close();
        conn.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

    return i;
}




public static class ReadFileThread extends Thread{
	String FilePath;
    public ReadFileThread(String filePath){  
        this.FilePath =filePath;  
    }  
	@Override
	public void run() {
		super.run();

		BufferedReader br;
		String[] field = new String[100000];
		List<String[]> fieldCollection = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(FilePath));
			String data = br.readLine();
			while( data!=null){
				field = data.split("\\,");
				fieldCollection.add(field);
			    data = br.readLine(); 
			}
			br.close();
			for (int i = 0; i < fieldCollection.size(); i++) {
    			if(fieldCollection.get(i).length < 11) return; // 小于11即代表该条数据缺少字段，暂且不录入
    			insert(fieldCollection.get(i));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
}
}


