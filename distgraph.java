import org.jfree.chart.ChartPanel;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class distgraph extends ApplicationFrame {
  double[] distance=new double[40];
  
  public distgraph( String applicationTitle , String chartTitle,double[] distf ) {
	  super(applicationTitle);
	  for(int i=0;i<distf.length;i++)
      {
    	  distance[i]=distf[i];
    	  
      }
      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,"No. of cycles","Total Distance",createDataset(),PlotOrientation.VERTICAL,true,true,false);
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      for(int i=0;i<distance.length;i++)
      {
      String cycle=Integer.toString(i+1);
      dataset.addValue(distance[i] , "distance" , cycle );
      }
      return dataset;
   }
    }	