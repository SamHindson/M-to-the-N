import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maine extends JPanel implements Runnable {

	Matrix initial = new Matrix(0, 0, 0.5f, 0.5f);
	int n = 1000;
	Matrix[] ms = new Matrix[n];

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Gravitas");
		f.setSize(1280, 720);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(new Maine());
		f.setVisible(true);
	}

	boolean drag1 = false;
	boolean drag2 = false;

	public Maine() {
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				drag1 = drag2 = false;
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (drag1) {
					initial.a = (e.getX() - 640) * 0.1f;
					initial.b = (e.getY() - 360) * 0.1f;
				} else if (drag2) {
					initial.c = (e.getX() - 640) * 0.1f;
					initial.d = (e.getY() - 360) * 0.1f;
				} else {
					//System.out.println(Math.abs(((e.getX()-640) * 0.1f) - initial.a));
					//System.out.println(Math.abs(((e.getY()-360) * 0.1f) - initial.b));
					if (Math.abs(((e.getX()-640) * 0.1f) - initial.a) < 2 && Math.abs(((e.getY()-360) * 0.1f) - initial.b) < 2)
						drag1 = true;
					else if (Math.abs(((e.getX()-640) * 0.1f) - initial.c) < 2 && Math.abs(((e.getY()-360) * 0.1f) - initial.d) < 2)
						drag2 = true;
				}
				
				Matrix k = new Matrix(initial);
				
				for(int j = 0; j < n; j++) {
					//System.out.println("Recalculating " + j + "...");
					k = Matrix.mat(k, initial);
					ms[j] = k;
				}
				//System.out.println("Finished recalculation.\n");
			}
		});
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(639, 0, 2, 720);
		g.fillRect(0, 359, 1280, 2);
		g.drawString("Initial Matrix: " + initial.toString(), 10, 720 - 10 - g.getFontMetrics().getHeight() * 2);
		g.setColor(Color.BLACK);
		g.drawLine((int) (10 * initial.a) + 640, (int) (10 * initial.b) + 360, (int) (10 * initial.c) + 640, (int) (10 * initial.d) + 360);
		
		// Blokes to make life easier
		g.setColor(drag1 ? Color.RED : new Color(0, 1f, 0, 0.5f));
		g.fillOval((int) (10 * initial.a) + 630, (int) (10 * initial.b) + 350, 20, 20);
		g.setColor(drag2 ? Color.RED : new Color(0, 0, 1f, 0.5f));
		g.fillOval((int) (10 * initial.c) + 630, (int) (10 * initial.d) + 350, 20, 20);
		
		// Others
		int k = 2;
		for(Matrix m : ms) {
			if(m != null) {
				int midx = (int) ((((10 * m.a) + 640) + (10 * m.c) + 640) / 2);
				int midy = (int) ((((10 * m.b) + 360) + (10 * m.d) + 360) / 2);
				if(m.valid) {
					g.setColor(Color.GRAY);
					g.drawLine((int) (10 * m.a) + 640, (int) (10 * m.b) + 360, (int) (10 * m.c) + 640, (int) (10 * m.d) + 360);
				} else {
					g.setColor(Color.RED);
				}
				g.drawString("n=" + k, midx, midy);
				k++;
			}
		}
	}
}
