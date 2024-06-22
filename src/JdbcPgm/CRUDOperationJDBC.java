package JdbcPgm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUDOperationJDBC {
	RegisterationBean rb=new RegisterationBean();
	Scanner scan=new Scanner(System.in);
	public static final String DRIVERNAME="com.mysql.cj.jdbc.Driver";
	public static final String URL="jdbc:mysql://localhost:3308/jdbcpgm_ebl";
	public static final String USERNAME="root";
	public static final String PASSWORD="";
	void getData()
	{
		System.out.println("Enter your Name ");
		rb.setName(scan.next());
		System.out.println("Enter the Email Id");
		rb.setEmailId(scan.next());
		System.out.println("Enter the Phone Number");
		rb.setPhoneNo(scan.nextLong());
	}
	void insertData()
	{
		try {
			Class.forName(DRIVERNAME);
			Connection connection=
DriverManager.getConnection(URL, USERNAME,PASSWORD);
PreparedStatement ps=
connection.prepareStatement("insert into registeration (name,emailId,phoneNo) values(?,?,?)");
ps.setString(1, rb.getName());
ps.setString(2, rb.getEmailId());
ps.setLong(3, rb.getPhoneNo());
int res=ps.executeUpdate();
if(res>0)
{
	System.out.println("Insert Successfully");
}
else
{
	System.out.println("Error in Insertion");
}
			
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	void reteriveData()
	{

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=
			DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbcpgm_ebl","root","");
			PreparedStatement ps=connection.prepareStatement("select * from registeration");
			ResultSet rs=ps.executeQuery();
		
			while(rs.next())
			{
				System.out.println("Register Number "+rs.getInt("sno"));
				System.out.println("Register Number "+rs.getString("name"));
				System.out.println("Register Number "+rs.getString("emailId"));
				System.out.println("Register Number "+rs.getLong("phoneNo"));
				System.out.println("*************next Record Details***********");

			
			}
		}
		catch(ClassNotFoundException cn)
		{
			System.out.println(cn);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		
		
		
	}
	void Update()
	{
		System.out.println("Enter the Sno for the person u want to Perform Update ");
		rb.setSno(scan.nextInt());
		System.out.println("Enter the Phone Number");
		rb.setPhoneNo(scan.nextLong());
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=
					DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbcpgm_ebl","root","");
		PreparedStatement preparedStatement=connection.
				prepareStatement("update registeration set phoneNo=? where sno=?  ");
		preparedStatement.setLong(1, rb.getPhoneNo());
		preparedStatement.setInt(2,rb.getSno());
		int res=preparedStatement.executeUpdate();
		if(res>0)
		{
			System.out.println("Update Successfully");
		}
		else
		{
			System.out.println("Error in Updation");
		}
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		
	}
	
	void delete()
	{  
		
		System.out.println("Enter the Serial number you want to delete");
		int serialNo=scan.nextInt();
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/jdbcpgm_ebl","root","");
		PreparedStatement ps = con.prepareStatement("delete from registeration where sno = ?;");
		
		ps.setInt(1,serialNo);
		int rs = ps.executeUpdate();
		if(rs>0){
			System.out.print("Delete Successfully");
			
		}
		else {
			System.out.println("Error in Deletion");
		}
	
	}
	catch(ClassNotFoundException ce){
		System.out.println(ce);
		
		
	}
	catch(SQLException se) {
		System.out.println(se);
	}
	}
	
	
	
	void choice()
	{
		int userSeletion;
		System.out.println("Enter your Choice ");
		System.out.println("1.Insert \n2.Reterive\n3.Delete\n4.Update");
		userSeletion=scan.nextInt();
		switch(userSeletion)
		{
		case 1: 
			this.getData();
			this.insertData();
			
			break;
		case 2:
			this.reteriveData();
			break;
		case 3:
			this.delete();
			break;
		case 4:
			this.reteriveData();
			this.Update();
			break;
		}
	
	}
	public static void main(String[] args) {
		CRUDOperationJDBC cObject=new CRUDOperationJDBC();
		cObject.choice();
		
		
		
		
	}

}
