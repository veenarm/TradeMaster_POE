package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.stefank.Main;

import viewcontroller.MaximizeButtonListener;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.SwingConstants;

public class MinimizedFrame extends JFrame implements IHideable {
	
	private JTextField txtTest;
	private boolean userWantsMinimize;
	private boolean isVisible;
	MainFrame mainFrame;
	
	
	public MinimizedFrame(MainFrame mainFrame)  {
		this.setTitle("MapTrado Mini");
		getContentPane().setForeground(Color.GRAY);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setAutoRequestFocus(false);
		setForeground(Color.GRAY);
		setFont(new Font("Calibri", Font.PLAIN, 12));
		setBackground(Color.GRAY);
		
		this.mainFrame = mainFrame;
		
		this.setDefaultLookAndFeelDecorated(true);
		
		JButton btnMaximize = new JButton("");
		btnMaximize.setLocation(14, 14);
		btnMaximize.setOpaque(false);
		btnMaximize.setForeground(Color.BLACK);
		btnMaximize.setContentAreaFilled(false);
		btnMaximize.setBorderPainted(false);
		Image maximizeIcon = null;
		try {
			maximizeIcon = ImageIO.read(Main.class.getResourceAsStream("maximize.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnMaximize.setIcon(new ImageIcon(maximizeIcon));
		btnMaximize.setFocusPainted(false);
		btnMaximize.setSize(32, 32);
		
		MaximizeButtonListener maxBtnListener = new MaximizeButtonListener(this, mainFrame);
		btnMaximize.addActionListener(maxBtnListener);
		getContentPane().setLayout(null);
		getContentPane().add(btnMaximize);
		
		
		this.setSize(60, 60);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().requestFocusInWindow();
	    FrameDragListener frameDragListener = new FrameDragListener(this);
	    this.addMouseListener(frameDragListener);
	    this.addMouseMotionListener(frameDragListener);
        this.setAlwaysOnTop(true);
		//frame.setMinimumSize(new Dimension(300, 300));
		isVisible = false;
		this.setVisible(false);
		
	}

	@Override
	public void setFrameVisible() {
		this.setVisible(true);
		isVisible = true;
	}

	@Override
	public boolean isUserWantsMinimize() {
		return userWantsMinimize;
	}
	
	public void setUserWantsMinimize(boolean userWantsMinimize) {
		this.userWantsMinimize = userWantsMinimize;
	}

	@Override
	public void setFrameInvisible() {
		this.setVisible(false);
		isVisible = false;
	}

	@Override
	public boolean isFrameVisible() {
		return isVisible;
	}
}
