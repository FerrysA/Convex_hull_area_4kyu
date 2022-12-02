# Convex_hull_area_4kyu
# 02.12.2022
# [cылка на задание](https://www.codewars.com/kata/59c1d64b9f0cbcf5740001ab)
# Код:
```java

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
```
# понравивщееся решение:(автор Itsukaz)
``` java
import java.util.ArrayList;
import java.text.DecimalFormat;
public class ConvexHull {    
    public static double getArea(double[][] points) {
        if(points.length<3)return 0;
        ArrayList<Integer> hull=new ArrayList<>();
        convexHull(points, points.length, hull);
        int len =hull.size()-1;
        double are=points[hull.get(len)][0]*points[hull.get(0)][1]-points[hull.get(len)][1]*points[hull.get(0)][0];
        for(int i=0;i<len;i++)
            are+=points[hull.get(i)][0]*points[hull.get(i+1)][1]-points[hull.get(i)][1]*points[hull.get(i+1)][0];
         return Double.valueOf(new DecimalFormat("0.00").format(are/2));
    }    
    static ArrayList<Integer> convexHull(double arr[][], int n,ArrayList<Integer> hull) 
    { 
        int left_most=0;
        for(int i=1;i<n;i++)
            if(arr[left_most][0]>arr[i][0])
                left_most=i;
        
        int p=left_most,q;
        do {            
            hull.add(p);
            q=(p+1)%n;
            for(int i=0;i<n;i++)
                if(check_orientation(arr[p],arr[i],arr[q])==2)
                    q=i;
            p=q;
        } while (p!=left_most);
        return hull;
    }   
   static int check_orientation(double []left_m,double []i,double []q){
       double val = (i[1]-left_m[1])*(q[0]-i[0])-(i[0]-left_m[0])*(q[1]-i[1]);
       return val==0?0:(val>0?1:2);
   }
}
