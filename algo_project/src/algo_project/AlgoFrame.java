package algo_project;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class AlgoFrame extends JFrame {
	
	private int CanvasWidth;
	private int CanvasHeight;
	public AlgoFrame(String title, int CanvasWidth, int CanvasHeight)
	{
		super(title);
		
		this.CanvasWidth = CanvasWidth;
		this.CanvasHeight = CanvasHeight;
		
		AlgoCanvas Canvas = new AlgoCanvas();
		setContentPane(Canvas);
		pack();//根据加载进窗口的内容重新整理布局
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	public AlgoFrame(String title)
	{
		this(title, 1024, 768);
	}
	
	public int getCanvasWidth(){return CanvasWidth;}
	public int getCanvasHeight(){return CanvasHeight;}
	
	private Circle[] circles;
	public void render(Circle[] circles)
	{
		this.circles = circles;
		this.repaint();
	}
	
	private class AlgoCanvas extends JPanel
	{
		public AlgoCanvas()
		{
			super(true);
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;//强制转换为Graphics2D（历史遗留问题）
			
			//打开抗锯齿
			RenderingHints hints = new RenderingHints(
											RenderingHints.KEY_ANTIALIASING, 
											RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.addRenderingHints(hints);
			
			//具体绘制
			AlgoVisHelper.setStrokeWidth(g2d, 1);
			AlgoVisHelper.setColor(g2d, Color.RED);
			for(Circle circle: circles)
				AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
			
			

		}
		
		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(CanvasWidth, CanvasHeight);
		}
	}
}
