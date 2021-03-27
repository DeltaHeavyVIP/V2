import methods.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;


public class Draw {
    private static void rawDraw(XYSeriesCollection lineDataset) {


        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "f(x)", "x",
                "y",
                lineDataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;
        int height = 1080;

        try {
            ChartUtils.saveChartAsJPEG(new File("src/main/resources/Chart.jpeg"), lineChart, width, height);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void draw(double a, double b, double lambda, double e, int number) {
        XYSeriesCollection lineDataset = new XYSeriesCollection();

        a = a - 1;
        b = b + 1;


        XYSeries function = new XYSeries("function");
        double len = b - a;
        for (double i = a; i <= b; i += len / 100) {
            if (number != 5) {
                function.add(i, Function.function(i, number));
            }else{
                function.add(i, i - lambda * Function.function(i, number));
            }
        }

        XYSeries zero = new XYSeries("zero");
        zero.add(a, 0);
        zero.add(b, 0);


        lineDataset.addSeries(function);
        lineDataset.addSeries(zero);

        rawDraw(lineDataset);

    }
}