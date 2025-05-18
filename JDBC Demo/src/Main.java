//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";
    private static final String username = "root";
    private static final String password = "WXby@79albe";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, username, password);

            //Only used Simple Statements
//            Statement statement = connection.createStatement();

            //For Inserting the data
//          String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)", "Rahul", 23, 47.5);

            //For Updating the data
//          String query = String.format("UPDATE students SET marks= %f WHERE id = %d", 82.5, 2);

            //For Deleting the data
//            String query = "DELETE FROM students WHERE ID = 2";
//            int rowsAffected = statement.executeUpdate(query);
//            if(rowsAffected>0){
//                System.out.println("Data Deleted Successfully!");
//            }else{
//                System.out.println("Data Not Deleted !");
//            }
            //For Retrieving data
//            String query = "SELECT * from Students";
//            ResultSet resultSet = statement.executeQuery(query);
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                int age = resultSet.getInt("age");
//                double marks = resultSet.getDouble("marks");
//                System.out.println("ID: " + id);
//                System.out.println("Name: " + name);
//                System.out.println("Age: " + age);
//                System.out.println("Marks: " + marks);
//            }

            //Used PreparedStatement for Inserting
//            String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,"Ankita");
//            preparedStatement.setInt(2,25);
//            preparedStatement.setDouble(3,84.7);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            if(rowsAffected>0){
//                System.out.println("Data Inserted Successfully!");
//            }else{
//                System.out.println("Data Not Inserted !");
//            }

            //Used PreparedStatement for Retrieving
//            String query = "SELECT marks FROM students WHERE id=?";
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            preparedStatement.setInt(1,1);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()){
//                Double marks = resultSet.getDouble("marks");
//                System.out.println("Marks: "+ marks);
//            }
//            else {
//                System.out.println("Not Found");
//            }

            //Used PreparedStatement for Updating
//            String query = "UPDATE students SET marks = ? WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setDouble(1,87.5);
//            preparedStatement.setInt(2,3);
//            int rowsAffected = preparedStatement.executeUpdate();
//            if(rowsAffected>0){
//                System.out.println("Data Updated Successfully!");
//            }else{
//                System.out.println("Data Not Updated !");
//            }

            //Now we will use Batch for executing lot of queries together using Simple Statement
//            Statement statement = connection.createStatement();
//            Scanner scanner = new Scanner(System.in);
//
//            while(true){
//                System.out.println("Enter Name: ");
//                String name = scanner.next();
//                System.out.println("Enter Age: ");
//                int age = scanner.nextInt();
//                System.out.println("Enter Marks: ");
//                double marks = scanner.nextDouble();
//                System.out.println("Enter more Data(Y/N): ");
//                String choice = scanner.next();
//                String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)", name, age, marks);
//                statement.addBatch(query);
//                if(choice.toUpperCase().equals("N")){
//                    break;
//                }
//            }
//            int[] arr = statement.executeBatch();

            //Now we will use Batch for executing lot of queries together using prepare Statement
//            String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            Scanner scanner = new Scanner(System.in);
//
//            while(true){
//                System.out.println("Enter Name: ");
//                String name = scanner.next();
//                System.out.println("Enter Age: ");
//                int age = scanner.nextInt();
//                System.out.println("Enter Marks: ");
//                double marks = scanner.nextDouble();
//                System.out.println("Enter more Data(Y/N): ");
//                String choice = scanner.next();
//                preparedStatement.setString(1,name);
//                preparedStatement.setInt(2,age);
//                preparedStatement.setDouble(3,marks);
//                preparedStatement.addBatch();
//                if(choice.toUpperCase().equals("N")){
//                    break;
//                }
//            }
//            int[] arr = preparedStatement.executeBatch();

            //Transaction Handling
            connection.setAutoCommit(false);
            String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Amount: ");
            double amount = scanner.nextDouble();
            debitPreparedStatement.setDouble(1, amount);
            debitPreparedStatement.setInt(2,101);
            creditPreparedStatement.setDouble(1,amount);
            creditPreparedStatement.setInt(2,102);
            debitPreparedStatement.executeUpdate();
            creditPreparedStatement.executeUpdate();
            if(isSufficient(connection, 101, amount)){
                connection.commit();
                System.out.println("Transaction Successfull");
            }else {
                connection.rollback();
                System.out.println("Transaction Failed");
            }
            debitPreparedStatement.close();
            creditPreparedStatement.close();
            scanner.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    static boolean isSufficient(Connection connection, int account_number, double amount){
        try {
            String query = "SELECT balance FROM accounts WHERE account_number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double current_balance = resultSet.getDouble("balance");
                if(amount>current_balance){
                    return false;
                }else{
                    return true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}