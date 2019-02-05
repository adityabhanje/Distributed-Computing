import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class finalg extends JComponent
{
	private TMAN[] nodes;
	private int N;
	private int k;
	private char t;
	public finalg(TMAN[] x,int N,int k,char s)
	{
		this.nodes = x;
		this.N=N;
		this.k=k;
		this.t=s;
	}
		
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 =(Graphics2D) g;
		double[] f=new double[3];
		double[] f1=new double[3];
		double x1,y1,x2,y2;
		for(int i=0;i<N;i++)
		{
			if(t=='B')
			{
				f=btop(nodes[i].id,N);
				x1=f[1];
				y1=f[2];
				
			for(int j=0;j<k;j++)
			{
				if(nodes[i].id!=N)
				{
				if(nodes[i].neighbor[j]==N)
				{
					x2=200;
					y2=0;
				}
				else
				{
				f=btop(nodes[i].neighbor[j],N);
				x2=f[1];
				y2=f[2];
				}
				Line2D.Double line= new Line2D.Double(x1,y1,x2,y2);
				g2.draw(line);
		     	}
			
				
			if(nodes[i].id==N)
			{
				x1=200;
				y1=0;
				if(nodes[i].neighbor[j]==1 || nodes[i].neighbor[j]==N-1)
				{
				f=btop(nodes[i].neighbor[j],N);
				x2=f[1];
				y2=f[2];
				Line2D.Double line= new Line2D.Double(x1,y1,x2,y2);
				g2.draw(line);
				}
			}
				


			}
			}
			
			if(t=='S')
			{
				f=spec(nodes[i].id,N);
				x1=f[1];
				y1=f[2];
				
			for(int j=0;j<k;j++)
			{
				f1=spec(nodes[i].neighbor[j],N);
				x2=f1[1];
				y2=f1[2];
				Line2D.Double line1= new Line2D.Double(x1,y1,x2,y2);
				g2.draw(line1);

			}
			
		}
		}
//				g2.draw(line1);
//		Line2D.Double line1= new Line2D.Double(nodes[i].x,nodes[i].y,nodes[nodes[i].neighbourList[j]].x,nodes[nodes[i].neighbourList[j]].y);
//		g2.draw(line1);
		
	}
	public static double[] btop(int nid,int n)
	{
		double[] f=new double[3];
		double pi=Math.PI;
		f[0]=((pi/2)-((nid-1)*(pi/(n-2))));
		f[1]=200+(200*Math.cos(f[0]));//x-coordinate
		f[2]=400+(200*Math.sin(f[0]));//y-coordinate
		return f;
	}
	public static double[] spec(int nid,int n)
	{
		double[] f=new double[3];
		double pi=Math.PI;
		if(nid>0 && nid<=((2*n)/5))
		{
			f[0]=((nid-1)*((5*pi)/(n)));
			f[1]=100+200*(1+(Math.cos(f[0])));//x-coordinate
			f[2]=200+200*Math.sin(f[0]);//y-coordinate
		}
		if(nid>((2*n)/5) && nid<=((3*n)/5))
		{
			f[0]=pi+(((nid-((2*n)/5))*((5*pi)/(n+5))));
			f[1]=100+200*(3+(Math.cos(f[0])));//x-coordinate
			f[2]=200+200*(Math.sin(f[0]));//y-coordinate
		}
		if(nid>((3*n)/5))
		{
			f[0]=(pi+((nid-((3*n)/5))*((5*pi)/(n))));
			f[1]=100+200*(5+(Math.cos(f[0])));//x-coordinate
			f[2]=200+200*(-(Math.sin(f[0])));//y-coordinate
		}
		return f;
	}


	
}
		
	

