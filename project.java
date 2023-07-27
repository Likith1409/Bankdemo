import java.util.*;

class Bank{
	static int count=0;
	Scanner sc=new Scanner(System.in);
	String name;
	int year;
	int acc;
	String type;
	double balance;

	Bank(){
		count++;
		System.out.println("\033[H\033[2J");
		System.out.println("Enter name:");
		name=sc.next();
		System.out.println("Enter year of birth:");
		year=sc.nextInt();
		acc=1000+(year%100)*10+count;
		System.out.println("Enter account type:");
		type=sc.next();
		balance=0;
		System.out.println("Your account number is: "+acc);
	}

	void Deposit(){
		System.out.println("\033[H\033[2J");
		System.out.println("Enter amount to deposit:");
		double amount=sc.nextDouble();
		balance+=amount;
	}

	void Withdraw(){
		System.out.println("\033[H\033[2J");
		System.out.println("Enter amount to withdraw:");
		double with=sc.nextDouble();
		if(with>balance)
			System.out.println("INSUFFICIENT BALANCE!");
		else 
		{
			System.out.println("Withdrew: "+with);
			balance-=with;
		}
	}

	void Display_Balance(){
		System.out.println();
		System.out.println("\033[H\033[2J");
		System.out.println("Balance: "+balance);
		System.out.println();
	}

	void Display(){
		System.out.println();
		System.out.println("\033[H\033[2J");
		System.out.println("Name: "+name);
		System.out.println("Year of Birth: "+year);
		System.out.println("Account Number: "+acc);
		System.out.println("Account Type: "+type);
		System.out.println("Balance: "+balance);
		System.out.println();
	}

	void Transfer(Bank ob){
		System.out.println("Enter amount to transfer:");
		double transfer=sc.nextDouble();
		if(transfer>balance)
			System.out.println("Insufficient balance!");
		else 
		{
			balance-=transfer;
			ob.balance+=transfer;
		}
	}
}

class project{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);

		Bank bank[]=new Bank[100];
		int i=-1, opt1, opt2;

		do{
			System.out.println();
			System.out.println("Select an option:");
			System.out.println("1. New account");
			System.out.println("2. Existing account");
			System.out.println("3. Exit");
			opt1=sc.nextInt();

			switch(opt1)
			{
				case 1: 
					if(i==-1)
						i=0;
					else 
						i=i+1;
					bank[i]=new Bank();
					break;

				case 2:
					if(i==-1)
						System.out.println("Open an account first!");
					else 
					{
						int j, flag=0, num;
						System.out.println("Enter account number:");
						num=sc.nextInt();
						for(j=0; j<=i; j++)
						{
							if(bank[j].acc==num)
							{
								flag=1;
								break;
							}
						}
						if(flag==0)
							System.out.println("Account not found!");
						else 
						{
							do{
								System.out.println();
								System.out.println("Select an option:");
								System.out.println("1. Display Balance");
								System.out.println("2. Display all Details");
								System.out.println("3. Withdraw");
								System.out.println("4. Deposit");
								System.out.println("5. Transfer to another account");
								System.out.println("6. Go Back");
								opt2=sc.nextInt();

								switch(opt2)
								{
									case 1:
										bank[j].Display_Balance();
										break;
									case 2:
										bank[j].Display();
										break;
									case 3:
										bank[j].Withdraw();
										break;
									case 4:
										bank[j].Deposit();
										break;

									case 5:
										int f=0;
										System.out.println("Enter account number to transfer to:");
										int tr=sc.nextInt();
										for(int k=0; k<=i; k++)
											if(bank[k].acc==tr)
												f=k;
										bank[j].Transfer(bank[f]);
										break;
								}
							}while(opt2!=6);							
						}
					}
					break;

				default:
					System.out.println("EXITING!");
					break;
			}
		}while(opt1!=3);
	}
}