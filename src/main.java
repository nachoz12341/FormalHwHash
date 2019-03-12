public class main {

    static int m= 1009;
    static int T[] = new int[m];
    static int R[] = new int[950];
    static boolean count=false;
    static int counter=0;

    private static void InitHashTable()
    {
        for(int i=0;i<m;i++)
        {
            T[i]=-1;
        }
    }

    private static void InitRandBag()
    {
        //Generate the first number
        int k=(int)Math.floor(Math.random()*50);
        R[0]=k;

        //Generate the rest of the numbers
        for(int i=1;i<950;i++)
        {
            k=k+(int)Math.floor(Math.random()*50);
            R[i]=k;
        }

        //Shuffle the list
        for(int i=0;i<950;i++)
        {
            int temp = R[i];
            k = (int)Math.floor(Math.random()*950);
            R[i]=R[k];
            R[k]=temp;
        }
    }

    private static int AuxHash(int k)
    {
        return k%m;
    }

    private static int AuxHashTwo(int k)
    {
        return 1+(k%11);
    }

    private static int LinearProbe(int k, int i)
    {
        return (AuxHash(k)+i)%m;
    }

    private static int QuadraticProbe(int k, int i)
    {
        return (AuxHash(k)+i+(int)Math.pow(i,2))%m;
    }

    private static int DoubleHash(int k, int i)
    {
        return (AuxHash(k)+(i*AuxHashTwo(k)))%m;
    }

    private static int HashInsert(int k,int type)
    {
        int i=0;
        while(i<m)
        {
            int j;

            if(type==0)
                j=LinearProbe(k,i);
            else if(type==1)
                j=QuadraticProbe(k,i);
            else
                j=DoubleHash(k,i);

            if(T[j]==-1)
            {
                T[j] = k;
                return j;
            }
            else
             {
                i++;
                if(count)
                    counter++;
            }
        }
        System.out.println(i);
        return -1;
    }

    public static void main(String args[])
    {
        //Linear probe
        InitHashTable();

        for(int i=0;i<900;i++) {
            HashInsert(R[i], 0);
        }

        count=true;
        counter=0;

        for(int i=0;i<50;i++)
        {
            HashInsert(R[i+900],0);
        }

        System.out.println("Linear probes: "+counter);


        //Quadratic probe
        InitHashTable();

        for(int i=0;i<900;i++) {
            HashInsert(R[i], 1);
        }

        count=true;
        counter=0;

        for(int i=0;i<50;i++)
        {
            HashInsert(R[i+900],1);
        }

        System.out.println("Quadratic probes: "+counter);


        //Double hash
        InitHashTable();

        for(int i=0;i<900;i++) {
            HashInsert(R[i], 2);
        }

        count=true;
        counter=0;

        for(int i=0;i<50;i++)
        {
            HashInsert(R[i+900],2);
        }

        System.out.println("Double hash probes: "+counter);
    }
}
