import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities;
import java.io.*;

public class TMAN
{
	int id;
	int [] neighbor;
	
	public static void main(String args[]) throws IOException
	{
		Scanner in =new Scanner(System.in);
		Random gen1=new Random();
		FileOutputStream out = null;//for neighbouring list nodes writing
		FileOutputStream out1=null;//for final distances writing
		int N;
		int k;
		char topo;
		//System.out.println("Enter The number of Nodes:");
		N=Integer.parseInt(args[0]);
		//System.out.println("Enter The number of Neighbors:");
		k=Integer.parseInt(args[1]);
		//System.out.println("Enter the type of topology: ");
		topo=args[2].charAt(0);
		String distfname=topo+"_N"+N+"_k"+k+".txt";
		out1=new FileOutputStream(distfname);
		TMAN [] obj=new TMAN[N];//initialise array of objects
		for(int i=0;i<N;i++)//assigning object values to array along with neighbor
		{
			obj[i]=new TMAN();
			obj[i].id=i+1;
			obj[i].neighbor=new int[k];
			obj[i].neighbor=ngen(k,N,obj[i].id);
			if(obj[i].id==N)
			{
				obj[i].neighbor[0]=N-1;
				obj[i].neighbor[1]=1;
			}
		    //System.out.println("Object Id: "+obj[i].id);
		    //System.out.println("Neighboring nodes are"+Arrays.toString(obj[i].neighbor));
		}
		//displaying initial distances of nodes from their neighbouring nodes
		double distc=0;double disti=0;
		for(int i=0;i<N;i++)
		{
			if(topo=='S')
			{
				double [] ics=new double[3];
				ics=spec(obj[i].id,N);
				double x1=ics[1];
				double y1=ics[2];
				for(int j=0;j<k;j++)
				{
					disti=0;
					double [] ic1s=new double[3];
					ic1s=spec(obj[i].neighbor[j],N);
					double x2=ic1s[1];
					double y2=ic1s[2];
					disti=dist(x1,x2,y1,y2);
					distc+=disti;
				}
			}
		
			if(topo=='B')
			{
			double [] ic=new double[3];
			ic=btop(obj[i].id,N);
			double x1=ic[1];
			double y1=ic[2];
			
			for(int j=0;j<k;j++)
			{
				disti=0;
				double [] ic1=new double[3];
				ic1=btop(obj[i].neighbor[j],N);
				double x2=ic1[1];
				double y2=ic1[2];
				if(obj[i].neighbor[j]==N)
				{
					if(obj[i].neighbor[j]==1 || obj[i].neighbor[j]==N-1)
					{
					disti=1;
					}
					if(1<obj[i].neighbor[j] && obj[i].neighbor[j]<N-1)
					{
						disti=0;
					}
				}
				if(obj[i].neighbor[j]==N)
				{
					if(obj[i].neighbor[j]==1 || obj[i].neighbor[j]==N-1)
					{
					disti=1;
					}
					if(1<obj[i].neighbor[j] && obj[i].neighbor[j]<N-1)
					{
						disti=0;
					}
				}

				if(obj[i].neighbor[j]!=N && obj[i].id!=N)
				{
				    disti=dist(x1,x2,y1,y2);
				}
				distc+=disti;
			}
				
	
			}
		}
		System.out.println("The final distance after initialisation is"+distc);
		String fwriteic="Sum of distances for initialisation phase is"+Double.toString(distc)+"\n ";
		byte[] convertic=fwriteic.getBytes();
		for(int yy1 = 0; yy1 < convertic.length ; yy1++) {
         out1.write( convertic[yy1] );}
		
		//variables initialisation
		int[] c=new int[2*k];//merged lists
		int r;//for random neighbor position from the neighbor array of node
		int a;//the exact random node from it's neighbor
		int temp1;//for sorting of received nodes
		int t;
		int l1;
		int[] c1;//merged list for itself
		int l2;
		int[] b;//till now sorting and removing duplicate of received nodes only
		double[] pc;//x,y,theta of node itself
		double[] d;//distance from neighboring nodes
		double x;
		double y;
		double[] xc1;
		double[] yc1;
		double[] pc1;//x,y of the neighboring nodes
		double temp2;//sorting of distance and final node starts
		int temp3;
		int flag;//final update starts
		int[] b1;//final updated list
		int[] f1;//final final updated list
		double[] pc2;
		double[] xc2;
		double[] yc2;
		double[] distance;
		double [] distf=new double[40];
		float df;
		double dist1;
		
		//variables for neighboring node's update list starts here
		int[] cn=new int[2*k];//merged list for neighboring node
		int tempn=0;
		int tempn1;
		int tn;
		int l1n;
		int[] c1n;//merged list for itself
		int l2n;
		int[] bn;//till now sorting and removing duplicate of received nodes only
		double[] pcn;//x,y,theta of node itself
		double[] dn;//distance from neighboring nodes
		double xn;
		double yn;
		double[] xc1n;
		double[] yc1n;
		double[] pc1n;//x,y of the neighboring nodes
		double temp2n;//sorting of distance and final node starts
		int temp3n;
		int[] f1n;
		double[] f6=new double[3];
		
		//evolution starts
		for(int ii=0;ii<40;ii++)
        {
          
          df=0;
          distance=new double[N];
		for(int i=0;i<N-1;i++)
		{
			//System.out.println("Node number is "+(i+1));
			r=gen1.nextInt(k);
			a=(obj[i].neighbor[r]);
			//System.out.println("object selected from neighbor is "+a);
			for(int j=0;j<k;j++)
			{
				tempn=obj[i].neighbor[j];
				if(tempn==a)
				{
					tempn=i+1;
				}
				cn[j]=obj[a-1].neighbor[j];
				cn[j+k]=tempn;
				
				c[j]=obj[i].neighbor[j];	
				c[j+k]=obj[a-1].neighbor[j];				
			}
			//System.out.println("Merged neighbor list 0f neighboring node is"+Arrays.toString(cn));
			//sorting of received nodes
			for(int a1=0;a1<(2*k)-1;a1++)
	        {
	            for(int a2=a1+1;a2<(2*k);a2++)
	            {
	                if(c[a1]>c[a2])
	                {
	                 temp1=c[a1];
	                 c[a1]=c[a2];
	                 c[a2]=temp1;
	                }
	                if(cn[a1]>cn[a2])
	                {
	                 tempn1=cn[a1];
	                 cn[a1]=cn[a2];
	                 cn[a2]=tempn1;
	                }
	                
	            }
	        }
			//System.out.println("Ascending order is"+Arrays.toString(cn));
			//duplicate removal 
			//removing duplicate starts
	        t=c[0];
	        l1=0;
	        c1=new int[2*k];
	        tn=cn[0];
	        l1n=0;
	        c1n=new int[2*k];
	        for(int i1=1;i1<c.length;i1++)
	        {
	        	if(t==c[i1])
	        	{
	        		continue;
	        	}
	        	c1[l1]=t;
	        	t=c[i1];
	        	l1++;
	        }
	        for(int i1=1;i1<cn.length;i1++)
	        {
	        	if(tn==cn[i1])
	        	{
	        		continue;
	        	}
	        	c1n[l1n]=tn;
	        	tn=cn[i1];
	        	l1n++;
	        }
	        c1[l1]=t;
	        c1n[l1n]=tn;
	        //System.out.println("Sorted received node is"+Arrays.toString(c1n));
	        //removing 0 from the unduplicate array starts
	        l2=0;
	        l2n=0;
	        for(int i1=0;i1<c1.length;i1++)
	        {
	            if(c1[i1]==0)
	            {
	                l2++;
	            }
	            if(c1n[i1]==0)
	            {
	                l2n++;
	            }
	        }
	        b=new int[c1.length-l2];
	        bn=new int[c1n.length-l2n];
	        for(int i2=0;i2<(c1.length-l2);i2++)
	        {
	            b[i2]=c1[i2];
	            
	        }
	        for(int i2=0;i2<(c1n.length-l2n);i2++)
	        {
	            bn[i2]=c1n[i2];
	            
	        }
	        //System.out.println("Final unduplicated list "+Arrays.toString(bn));
	        
	        //distance finding starts
	        x=0.0;
	        y=0.0;
	        xn=0.0;
	        yn=0.0;
	        if(topo=='B')
	        {
	        	pc=new double[3];
	        	pc=btop(obj[i].id,N);
	 	        x=pc[1];
	 			y=pc[2];
	 			pcn=new double[3];
		        pcn=btop(obj[a-1].id,N);
		        xn=pcn[1];
				yn=pcn[2];
	        }
	        if(topo=='S')
	        {
	        	pc=new double[3];
	        	pc=spec(obj[i].id,N);
	 	        x=pc[1];
	 			y=pc[2];
	 			pcn=new double[3];
		        pcn=spec(obj[a-1].id,N);
		        xn=pcn[1];
				yn=pcn[2];
	        }
	        xc1=new double[b.length];
			yc1=new double[b.length];
			pc1=new double[3];
			d=new double[b.length];
			
			pcn=new double[3];
	        xc1n=new double[bn.length];
			yc1n=new double[bn.length];
			pc1n=new double[3];
			dn=new double[bn.length];
			if(topo=='B')
			{
			for(int j=0;j<b.length;j++)
			{
				pc1=btop(b[j],N);
				xc1[j]=pc1[1];
				yc1[j]=pc1[2];
//				if(obj[i].id==N)
//				{
//					if(b[j]==1 || b[j]==N-1)
//					{
//					d[j]=1;
//					}
//					if(b[j]>1 && b[j]<N-1)
//					{
//						d[j]=Double.MAX_VALUE;
//					}	
//				}
				if(b[j]==N)
				{
					if(obj[i].id==1 || obj[i].id==N-1)
					{
					d[j]=1;
					}
					if(1<obj[i].id && obj[i].id<N-1)
					{
						d[j]=Double.MAX_VALUE;
					}
				}
				if(b[j]!=N)
				{
				    d[j]=dist(x,xc1[j],y,yc1[j]);
				}
			}
			for(int j=0;j<bn.length;j++)
			{
				pc1n=btop(bn[j],N);
				xc1n[j]=pc1n[1];
				yc1n[j]=pc1n[2];
				if(bn[j]==N)
				{
					if(obj[a-1].id==1 || obj[a-1].id==N-1)
					{
					dn[j]=1;
					}
					if(1<obj[a-1].id && obj[a-1].id<N-1)
					{
						dn[j]=Double.MAX_VALUE;
					}
				}
				
				if(obj[a-1].id==N)
				{
					if(bn[j]==1 || bn[j]==N-1)
					{
					dn[j]=1;
					}
					if(bn[j]>1 && bn[j]<N-1)
					{
						dn[j]=Double.MAX_VALUE;
					}	
				}
				if(bn[j]!=N && obj[a-1].id!=N)
				{
				    dn[j]=dist(xn,xc1n[j],yn,yc1n[j]);
				}
			}
			}
			if(topo=='S')
			{
			for(int j=0;j<b.length;j++)
			{
				pc1=spec(b[j],N);
				xc1[j]=pc1[1];
				yc1[j]=pc1[2];
				d[j]=dist(x,xc1[j],y,yc1[j]);
				
			}
			for(int j=0;j<bn.length;j++)
			{
				pc1n=spec(bn[j],N);
				xc1n[j]=pc1n[1];
				yc1n[j]=pc1n[2];
				dn[j]=dist(xn,xc1n[j],yn,yc1n[j]);
				
			}
			}
						
			//System.out.println("Distances are "+Arrays.toString(dn));
			//sorting according to distance only, of the neighboring list also
			for(int a1=0;a1<d.length-1;a1++)
	        {
	            for(int a2=a1+1;a2<d.length;a2++)
	            {
	                if(d[a1]>d[a2])
	                {
	                 temp2=d[a1];
	                 d[a1]=d[a2];
	                 d[a2]=temp2;
	                 temp3=b[a1];
	                 b[a1]=b[a2];
	                 b[a2]=temp3;
	                }
	            }
	        }
			for(int a1=0;a1<dn.length-1;a1++)
	        {
	            for(int a2=a1+1;a2<dn.length;a2++)
	            {
	                if(dn[a1]>dn[a2])
	                {
	                 temp2n=dn[a1];
	                 dn[a1]=dn[a2];
	                 dn[a2]=temp2n;
	                 temp3n=bn[a1];
	                 bn[a1]=bn[a2];
	                 bn[a2]=temp3n;
	                }
	            }
	        }
			//System.out.println("After Sorting according to distance: "+Arrays.toString(bn));
			//removing itself from it's neighboring list
			flag=0;
			//System.out.println("B array is"+Arrays.toString(b));
			for(int i1=0;i1<b.length;i1++)
			{
				if(b[i1]==obj[i].id)
				{
					flag++;
					break;
				}
			}
			if (flag==1)
			{
				b1=new int[b.length-1];
				for(int i1=1;i1<b.length;i1++)
				{
					b1[i1-1]=b[i1];
				}
			}
			else
			{
				b1=new int[b.length];
				for(int i1=0;i1<b.length;i1++)
				{
					b1[i1]=b[i1];
				}
			}
			//System.out.println("Updated final list is"+Arrays.toString(b1));
			f1=new int[k];
			for(int i1=0;i1<k;i1++)
			{
				f1[i1]=b1[i1];
			}
			f1n=new int[k];
			for(int i1=0;i1<k;i1++)
			{
				f1n[i1]=bn[i1];
			}
			//updating the neighbor lists finally
			for(int i1=0;i1<k;i1++)
			{
				obj[i].neighbor[i1]=f1[i1];
				obj[a-1].neighbor[i1]=f1n[i1];
				
			}
		}
		for(int i=0;i<N;i++)
		{
			//calculating new final distance
			pc2=new double[3];
			xc2=new double[k];
			yc2=new double[k];
			if(topo=='B')
			{
				pc=new double[3];
	        	pc=btop(obj[i].id,N);
	 	        x=pc[1];
	 			y=pc[2];
			for(int j=0;j<obj[i].neighbor.length;j++)
			{
				dist1=0;
				pc2=btop(obj[i].neighbor[j],N);
				xc2[j]=pc2[1];
				yc2[j]=pc2[2];
				if(obj[i].neighbor[j]==N)
				{
					if(obj[i].id==1 || obj[i].id==N-1)
					{
					dist1=1;
					}
					if(1<obj[i].id && obj[i].id<N-1)
					{
						dist1=0;
					}
				}
				if(obj[i].id==N)
				{
					if(obj[i].neighbor[j]==1 || obj[i].neighbor[j]==N-1)
					{
					dist1=1;
					}
					if(1<obj[i].neighbor[j] && obj[i].neighbor[j]<N-1)
					{
						dist1=0;
					}
				}

				if(obj[i].neighbor[j]!=N && obj[i].id!=N)
				{
				    dist1=dist(x,xc2[j],y,yc2[j]);
				}
				distance[i]+=dist1;
				}
			      
			}
			if(topo=='S')
			{
				pc=new double[3];
	        	pc=spec(obj[i].id,N);
	 	        x=pc[1];
	 			y=pc[2];
				for(int j=0;j<obj[i].neighbor.length;j++)
				{
					dist1=0;
					pc2=spec(obj[i].neighbor[j],N);
					xc2[j]=pc2[1];
					yc2[j]=pc2[2];
					dist1=dist(x,xc2[j],y,yc2[j]);
					distance[i]+=dist1;
				}
			}

			df=df+(float)(distance[i]);
		}
				System.out.println("The final distance  for "+(ii+1)+" cycle is: "+df);
				distf[ii]=df;
				String fwrite1="Sum of distances after cycle no. "+(ii+1)+" is: "+Double.toString(distf[ii])+"\n ";
				byte[] convert1=fwrite1.getBytes();
				for(int yy1 = 0; yy1 < convert1.length ; yy1++) {
		         out1.write( convert1[yy1] );}
				
                //plotting of graphs for 1,5,10,15 cycles and saving it in the folder where the files are stored
				if(ii==0 || ii==4 ||ii==9 ||ii==14)
				{
					final XYSeries plotn = new XYSeries( "plotn",false,true );
					double[] f3=new double[3];
					double [] f4=new double[3];
					double x3,y3,x4,y4;
					if(topo=='B')
					   { 
						   plotn.add(1,3);
						   for(int i=0;i<N-1;i++)
						   {
							    if(obj[i].id!=N)
							   { 
							   	f3=btop(obj[i].id,N);
							   	x3=f3[1];
								y3=f3[2];
								
							for(int j=0;j<obj[i].neighbor.length;j++)
							{
								
								f4=btop(obj[i].neighbor[j],N);
								x4=f4[1];
								y4=f4[2];
								plotn.add(x3,y3);
								plotn.add(x4,y4);
							}
						   }
			    }
					   
						   }
						   if(topo=='S')
							{
								for(int i=0;i<N;i++)
								{
							   f3=spec(obj[i].id,N);
								x3=f3[1];
								y3=f3[2];
								
							for(int j=0;j<k;j++)
							{
								f4=spec(obj[i].neighbor[j],N);
								x4=f4[1];
								y4=f4[2];
								plotn.add(x3,y3);
								plotn.add(x4,y4);
							}
								}
						}
						   final XYSeriesCollection dataset = new XYSeriesCollection();
						      dataset.addSeries( plotn );
						   JFreeChart xylineChart = ChartFactory.createXYLineChart(
							         "Plotting of nodes", 
							         "X Co-ordinate",
							         "Y Co-ordinate", 
							         dataset,
							         PlotOrientation.VERTICAL, 
							         true, true, false);
							      
							      int width = 960;   /* Width of the image */
							      int height = 640;  /* Height of the image */
							      String filename=topo+"_N"+N+"_k"+k+"_"+(ii+1)+".jpeg";
							      File XYChart = new File( filename ); 
							      ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
					
					String fname=topo+"_N"+N+"_k"+k+"_"+(ii+1)+".txt";
					out = new FileOutputStream(fname);

					for(int i=0;i<N;i++)
					{
						String fwrite="The neighbouring list of "+(i+1)+" node is "+Arrays.toString(obj[i].neighbor)+"\n";
						byte[] convert=fwrite.getBytes();
						for(int yy = 0; yy < convert.length ; yy++) {
				         out.write( convert[yy] );
						}
					}
        }
		
		}
		out.close();
		out1.close();
		// for plotting of nodes
		JFrame window = new JFrame();
		window.setSize(800,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		finalg DC = new finalg(obj,N,k,topo);
		window.add(DC);
      
		//for distances graph
		distgraph chart = new distgraph("Graph for distances","Sum of total distances for each cycle",distf);
        chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
		double[] f5=new double[3];
		f5=btop(1,N);
	}
	public static int[] ngen(int k,int n,int nid)  //k-no. of neighbors, n-no. of nodes, nid-nodeid
	{
		int[] a=new int[k];
		Random gen=new Random();
		int x;
		for(int i=0;i<k;i++)
		{
			x=gen.nextInt(n)+1;
			while(x==nid)
			{
				x=gen.nextInt(n)+1;
			}
			a[i]=x;
			for(int j=0;j<i;j++)
			{
				if(a[j]==x)
				{
					i--;
					continue;
				}
			}		
		}
	    return a;	
	}
	public static double[] btop(int nid,int n)
	{
		double[] f=new double[3];
		double pi=Math.PI;
		f[0]=((pi/2)-((nid-1)*(pi/(n-2))));
		f[1]=1+Math.cos(f[0]);//x-coordinate
		f[2]=1-Math.sin(f[0]);//y-coordinate
		return f;
	}
	
	public static double[] spec(int nid,int n)
	{
		double[] f=new double[3];
		double pi=Math.PI;
		if(nid>0 && nid<=((2*n)/5))
		{
			f[0]=((nid-1)*((5*pi)/(n)));
			f[1]=1+(Math.cos(f[0]));//x-coordinate
			f[2]=Math.sin(f[0]);//y-coordinate
		}
		if(nid>((2*n)/5) && nid<=((3*n)/5))
		{
			f[0]=(pi+((nid-((2*n)/5))*((5*pi)/(n+5))));
			f[1]=3+(Math.cos(f[0]));//x-coordinate
			f[2]=-(Math.sin(f[0]));//y-coordinate
		}
		if(nid>((3*n)/5))
		{
			f[0]=(pi+((nid-((3*n)/5))*((5*pi)/(n))));
			f[1]=5+(Math.cos(f[0]));//x-coordinate
			f[2]=-(Math.sin(f[0]));//y-coordinate
		}
		return f;
	}
	
	public static double dist(double x1,double x2,double y1,double y2)
	{
		double d=0;
		d=Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		return d;
	}
}
