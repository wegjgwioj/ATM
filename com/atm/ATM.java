package com.atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accounts = new ArrayList<>();
    private Account loginAcc;
    private Scanner sc = new Scanner(System.in);

    //欢迎
    public void start() {
        while (true) {
            System.out.println("======Welcome====");
            System.out.println("====1--login====");
            System.out.println("====2--create account====");
            System.out.println("====please--select====");
            int command = sc.nextInt();
            switch (command) {
                case 1 -> {
                    System.out.println("deng lu");
                    Login();
                }
                case 2 -> {
                    System.out.println("kai hu");
                    createAccount();
                }
                default -> System.out.println("not valve");
            }
        }
    }

    //登录功能
    private void Login() {
        System.out.println("login ui");
        if (accounts.size() == 0) {
            System.out.println("The account is empty");
            System.out.println("Please create one first");

            return;
        }
        while (true) {
            System.out.println("please insert id ");
            String carId = sc.next();
            //
            Account acc = getAccountByCardId(carId);
            if (acc == null) {
                System.out.println("not exist");

            } else {
                while (true) {
                    System.out.println("please insert Password");
                    int PassWord = sc.nextInt();
                    if (acc.getPassWord() == PassWord) {
                        System.out.println("logging success   " + acc.getName() + "  your id is   " + acc.getId());
                        //展示功能
                        loginAcc = acc;
                        System.out.println("===========================");
                        showCommand();
                        return;
                    } else {
                        System.out.println("please insert password again");
                    }
                }

            }

        }

    }

    //登录后操作界面
    private void showCommand() {
        while (true) {
            System.out.println(loginAcc.getName() + "========Follow  Command ======");
            System.out.println("1>select");
            System.out.println("2>addMoney");
            System.out.println("3>takeMoney");
            System.out.println("4>transferAccount" );
            System.out.println("5>overWritePassWord");
            System.out.println("6>exit");
            System.out.println("7>deleteAccount");
            System.out.println("==========================");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    select();
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    takeMoney();
                    break;
                case 4:
                    transferAccount();
                    break;
                case 5:
                    if (overWritePassWord()){ return;}
                     else{ break;}
                case 6:
                    System.out.println(loginAcc.getName() + "exit success");
                    return;
                case 7:
                    if (deleteAccount()) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid commands");
            }
        }
    }
    //修改密码
    private boolean overWritePassWord() {
        System.out.println("Please enter your password to confirm your identity ");
        int password= sc.nextInt();
        while (true) {
            if (loginAcc.getPassWord()==password) {
                System.out.println("Are you sure you want to Change the account PassWord y/n");
               String command=sc.next();
                switch (command){
                    case "y":
                        while (true) {
                            System.out.println("new number PassWord");
                            int Password= sc.nextInt();
                            System.out.println("Confirm new number PassWord");
                            int okPassword= sc.nextInt();
                            if(okPassword==Password){
                                loginAcc.setPassWord(Password);
                                System.out.println(" Changed!!");
                                return true;
                            }else{
                                System.out.println("not equals");
                            }
                        }
                    default:
                        return false;
                }
            }else {
                System.out.println("Wrong password");
                return false;
            }
        }
    }

    //销户
    private boolean deleteAccount() {
         System.out.println("Are you sure you want to destroy the account  y/n");
         String command=sc.next();
         switch (command){
             case "y":
                 if (loginAcc.getPrice() != 0) {
                     System.out.println(" your price not empty ! Destroy failed!!! ");
                     return false;
                 }else {
                     accounts.remove(loginAcc);
                     System.out.println("Destroy success!!");
                     return true;
                 }
             default:
                 return false;

         }

    }

    //转钱
        private void transferAccount() {
        System.out.println("==============");
        if (accounts.size() <2) {
            System.out.println("not enough account");
             return;
        }

        if (loginAcc.getPrice()==0){
            System.out.println("not enough price");
            return;
        }
        while (true) {
            System.out.println("insert Recipients---id");
            String cardId=sc.next();
            Account Recipients=getAccountByCardId(cardId);
            if (Recipients == null) {
                System.out.println("not exist that account");
            }else{
                System.out.println("exist that account");
                System.out.println("how much you want to transfer");
                double TransferMoney= sc.nextDouble();
                if (TransferMoney > loginAcc.getWithdrawalAmount()) {
                    System.out.println("over WithdrawalAmount");
                    System.out.println("TransferMoney Success!");
                    break;
                }else{
                    Recipients.setPrice(Recipients.getPrice()+TransferMoney);
                    loginAcc.setPrice(loginAcc.getPrice()-TransferMoney);
                    System.out.println("TransferMoney Success");
                    System.out.println("==============");
                    return;
                }
            }
        }

    }
        //取钱
        private void takeMoney() {
        System.out.println("====taking money====");
        if (loginAcc.getPrice()<100){
            System.out.println("money<100 false");
            return;
        }
        //输入合法的取钱金额
        while (true) {
            System.out.println("how much you want to take off");
            double money=sc.nextDouble();
            if ( money>loginAcc.getWithdrawalAmount()) {
                System.out.println("over the WithdrawalAmount"+loginAcc.getWithdrawalAmount());

            }else if (money >loginAcc.getPrice()) {
                System.out.println("price too small"+loginAcc.getPrice());
                System.out.println("if you want to exit please select 1");
                String command =sc.next();
                switch (command){
                    case "1":
                        return;
                    default:
                        System.out.println("please select Suitable money!");
                }
            }else {
                loginAcc.setPrice(loginAcc.getPrice() - money);
                System.out.println("success"+"  remainder" +loginAcc.getPrice());
              break;
            }

        }


    }

        //存钱
        private void addMoney() {
       System.out.println("how much");
       double money =sc.nextDouble();
       loginAcc.setPrice(loginAcc.getPrice()+money);
        System.out.println(" add "+loginAcc.getPrice() );
    }

        private void select(){
           System.out.println("Account Information");
           System.out.println("id="+loginAcc.getId());
           System.out.println("Name="+loginAcc.getName());
           System.out.println("Price=" +loginAcc.getPrice());
           System.out.println("Sex="+loginAcc.getSex());
           System.out.println("WithdrawalAmount="+loginAcc.getWithdrawalAmount());
       }
       //开通
        private void createAccount(){
          //创建一个账户对象
            Account ac=new Account();
            //输入信息赋值
            System.out.println("name");
            String name= sc.next();
            ac.setName(name);

            while (true) {
                System.out.println("sex");
                char sex= sc.next().charAt(0);
                if (sex == '男'||sex == '女') {
                    ac.setSex(sex);
                    break;
                }else {
                    System.out.println("please insert again");
                }
            }

            while (true) {
                System.out.println("number PassWord");
                int Password= sc.nextInt();
                System.out.println("Confirm number PassWord");
                int okPassword= sc.nextInt();
                if(okPassword==Password){
                    ac.setPassWord(Password);
                     break;
                }else{
                    System.out.println("not equals");
                }
            }
            //取钱额度
            System.out.println("WithdrawalAmount");
            double WithdrawalAmount= sc.nextDouble();
            ac.setWithdrawalAmount(WithdrawalAmount);
             //卡号
            String newCardId = CreateId();
            ac.setId(newCardId);
            //存入信息
             accounts.add(ac);
            System.out.println("create "+ac.getName()+"successful and your id is   "+ac.getId());

        }
        //生成卡号方法
         private String CreateId(){
             while (true) {
                 String CardId="";

                 Random r= new Random();
                 for (int i = 0; i <8 ; i++) {
                     int data =r.nextInt(10);
                     CardId += data;
                 }
                 //判断是否重复
                 Account acc=getAccountByCardId(CardId);
                 if (acc == null) {
                     return  CardId;
                 }
             }
         }
       //判断卡号是否存在
         private Account getAccountByCardId(String cardId){
             for (Account acc : accounts) {
                 if (acc.getId().equals(cardId)) {
                     return acc;
                 }
             }
             return null;
         }

}

