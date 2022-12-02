
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;// библ для ввода с клавиатуры

public class Main {


    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    static class Point
    {

        public double x;



        public double y;
        Point(double xx ,double yy)
        {


            x=xx;
            y=yy;
        }

        public static double S_triangle(Point P,Point Q,Point R)
        {

            double a =Len(P,Q);
            double b =Len(Q,R);
            double c =Len(R,P);
            double p= (a+b+c)/2;
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));

        }

        public static double Len(Point P,Point Q)
        {

            double a=P.x-Q.x;
            double b=P.y-Q.y;

            return Math.sqrt(a*a+b*b);
        }

         static double Corner(Point P,Point Q)
        {

            if(Q.x>P.x && Q.y>=P.y)//1
            {

                return corner(P,Q);
            }
            if(Q.x<=P.x && Q.y>P.y)//2
            {

                return Math.PI- corner(P,Q);
            }
            if(Q.x<P.x && Q.y<=P.y)//3
            {

                return Math.PI+ corner(P,Q);

            }
            if(Q.x>=P.x && Q.y<P.y)//4
            {

                return 2*Math.PI- corner(P,Q);
            }
            return 0;
        }

        static double corner(Point P,Point Q)// относ P
        {
            if((Q.x-P.x)!=0)
            {
                double Res=Math.atan(Math.abs((Q.y-P.y)/(Q.x-P.x)));
                return  Res;
            }

            return Math.PI/2;
        }



        public String toString() {

            return "{"+x+","+y+"}";
        }

    }

    static class Vector
    {
        double x=0;
        double y=0;
        Vector(double xx, double yy)
        {
            x=xx;
            y=yy;
        }
        Vector(Point A, Point B)// B конец
        {
            x=B.x-A.x;
            y=B.y-A.y;
        }

        static double Arg(Vector P)
        {

            if(P.x>0 && P.y>=0)//1
            {

                return arg(P);
            }
            if(P.x<=0 && P.y>0)//2
            {

                return Math.PI- arg(P);
            }
            if(P.x<0 && P.y<=0)//3
            {

                return Math.PI+ arg(P);

            }
            if(P.x>=0 && P.y<0)//4
            {

                return 2*Math.PI- arg(P);
            }
            return 0;
        }

        static double arg(Vector P)// относ P
        {
            if((P.x)!=0)
            {
                double Res=Math.atan(Math.abs((P.y)/(P.x)));
                return  Res;
            }

            return Math.PI/2;
        }
        static double Corner(Vector P,Vector Q)//  угол от П до Ку по часовой стрелке
        {
            if(Arg(P)>Arg(Q))
            {
                return 2*Math.PI-(Arg(P)-Arg(Q));
            }
            if(Arg(P)<Arg(Q))
            {
                return (Arg(Q)-Arg(P));
            }
            return 2*Math.PI;
        }
    }


    public static double getArea(double[][] points) {
        double[][] A =points;
        int n=A.length;// число точек

        if(n==0)
        {
            return 0;
        }
        double Res=0;
       Point[] P =new Point[n];
        {
            for (int i=0;i<n;i++)
            {
                P[i]=new Point(A[i][0],A[i][1]);
            }
        }
        // find down

            Point Start=P[0];
            int Start_i=0;
            for (int i=0;i<n;i++)
            {
                if(P[i].y<Start.y)
                {
                    Start =P[i];
                    Start_i=i;
                }
            }


        Point B=Start;
        Point C=Start;
        Vector s=new Vector(-1,0);
        Vector b=s;
        Vector c;
        Vector find;
        int C_i=Start_i;
        int find_C_i=0;
        int Rem_C_i=0;
        double min=2*Math.PI;
        double findCor;
        for (int i=0;i<n;i++)
        {

            for (int j=0;j<n;j++)
            {
                if(j!=C_i)
                {
                    find=new Vector(C,P[j]);
                    findCor=Vector.Corner(b,find);

                    if(findCor<=min)
                    {
                        min=findCor;
                        find_C_i=j;
                    }
                }

            }
            {
                if(find_C_i==Start_i)
                {
                    return round(Res,2);
                }
            }
            B=C;
            C=P[find_C_i];
            Rem_C_i=C_i;
            C_i=find_C_i;
            b=new Vector(C,B);
            min=2*Math.PI;



            Res+=Point.S_triangle(Start,B,C);
        }




        return round(Res,2);

    }
    private static Scanner inn = new Scanner(System.in);// метод для ввода с клавиатуры

    public static <string> void main(String[] args) {



        Point A=new Point(0,3);
        Point B =new Point(1,2);
        Point C =new Point(4,1);

        //double[][]P = {{0, 3}, {1, 2}, {3, 4},{4,1},{5,6}};
        double[][]P =  {{0, 5}, {5, 0}, {-5, 0}, {-3, -5}, {3, -5}, {0, 3}, {3, 0}, {-3, 0}, {-1, -3}, {1, -3}};









        System.out.println(getArea(P));




    }
}

