import java.sql.SQLOutput;

public class Lab01
{
    public static void main(String[] args)
    {
        Lab01 lab1=new Lab01();
        lab1.compulsory();
        lab1.homework(args);
        lab1.bonus();
    }
    void compulsory()
    {
        System.out.println("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);

        n=n*3;
        n=n+0b10101;
        n=n+0xFF;
        n=n*6;

        while(n>9)
        {
            int sum=0;
            while(n>0)
            {
                sum+=n%10;
                n/=10;
            }
            n=sum;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);

    }

    void homework(String[] args)
    {
        long startTime=System.currentTimeMillis();

        if(args.length>3)
            throw new IllegalArgumentException("Numarul de parametri este invalid");

        int a=Integer.parseInt(args[0]);
        int b=Integer.parseInt(args[1]);
        int k=Integer.parseInt(args[2]);

        if(a<0 || b<0 || k<0)
            throw new IllegalArgumentException("Parametri nu sunt valizi");

        StringBuilder string = new StringBuilder();

        for(int i=a;i<=b;i++)
        {
            int n=i;
            while(n>9)
            {
                int sum=0;
                while(n>0)
                {
                    sum+=(n%10)*(n%10);
                    n/=10;
                }
                n=sum;
            }
            if(n==k)
            {
                string.append(i);
                string.append(" ");
            }
        }
        System.out.println(string);

        long endTime=System.currentTimeMillis();
        long solTime=endTime-startTime;

        System.out.println("Running time in milliseconds is: " + solTime);
    }

    void bonus()
    {
        int n=6;
        int[][] adjMatrix = new int[n+1][n+1];

        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                adjMatrix[i][j]=0;

        for(int j=1;j<n;j++)
        {
            adjMatrix[n][j] = 1;
            adjMatrix[j][n]=1;
        }

        for(int i=1;i<n;i++)
        {
            if(i==1)
            {
                adjMatrix[i][i+1]=1;
                adjMatrix[i][n-1]=1;
            }
            else if(i==n-1)
            {
                adjMatrix[i][1]=1;
                adjMatrix[i][i-1]=1;
            }
            else
            {
                adjMatrix[i][i+1]=1;
                adjMatrix[i][i-1]=1;
            }
        }

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++)
            {
                System.out.print(adjMatrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }

        int nr=4;
        int contor=1;

        int f=nr*nr-3*nr+3;

        System.out.print("circuit no. " + contor + ": ");
        for(int i=1;i<nr;i++)
            {
                System.out.print(i);
                System.out.print(" ");
            }
        System.out.println();
        for(int i=1;i<nr-1;i++)
            for(int j=i+1;j<nr;j++)
            {
                contor++;
                System.out.print("circuit no. " + contor + ": ");
                System.out.print(i);
                System.out.print(" ");
                System.out.print(nr);
                System.out.print(" ");
                for(int k=(j+1)%nr;k!=i;k=(k+1)%nr)
                {
                    if(k!=0 && k!=nr)
                    {
                        System.out.print(k);
                        System.out.print(" ");
                    }

                }
                System.out.print(j);
                System.out.print(" ");
                System.out.println();

                contor++;
                System.out.print("circuit no. " + contor + ": ");
                System.out.print(i);
                System.out.print(" ");
                System.out.print(nr);
                System.out.print(" ");
                for(int l=j-1;l!=i;l--)
                {
                    if(l!=0 && l!=nr)
                    {
                        System.out.print(l);
                        System.out.print(" ");
                    }
                }
                System.out.print(j);
                System.out.print(" ");
                System.out.println();
            }


        System.out.println("Numarul de circuite este: " + contor);
        if(f==contor)
            System.out.println("The formula is correct!");
        else
            System.out.println("The formula is incorrect!");
    }
}