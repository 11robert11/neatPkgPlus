package pkgPluss;//HIDE

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Text implements Shape
{
    private Color color = Color.BLACK;
    private final JLabel label = new JLabel();
    private double x;
    private double y;
    private double xGrow;
    private double yGrow;
    private Text self;

    /**
     * Constructs a text at a given location.
     * @param x the leftmost x-position of the shape
     * @param y the topmost y-position of the shape
     * @param message the text string
     */
    public Text(double x, double y, String message)
    {
        this.x = x;
        this.y = y;
        label.setText(message);
    }


    /**
     * Gets the leftmost x-position of the bounding box.
     * @return the leftmost x-position
     */
    public int getX()
    {
        return (int) Math.round(x - xGrow) ;
    }

    /**
     * Gets the topmost y-position of the bounding box.
     * @return the topmost y-position
     */
    public int getY()
    {
        return (int) Math.round(y - yGrow);
    }


    /**
     * Gets the width of the bounding box.
     * @return the width
     */
    public int getWidth()
    {
        return (int) Math.round(label.getPreferredSize().getWidth() + 2 * xGrow);
    }

    /**
     * Gets the height of the bounding box.
     * @return the height
     */
    public int getHeight()
    {
        return (int) Math.round(label.getPreferredSize().getHeight() + 2 * yGrow);
    }

    /**
     * Moves this text by a given amount.
     * @param dx the amount by which to move in x-direction
     * @param dy the amount by which to move in y-direction
     */
    public Text translate(double dx, double dy)
    {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
        return this;
    }

    /**
     *
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public Text setPos(double x, double y)  {
        this.x = x;
        this.y = y;
        Canvas.getInstance().repaint();
        return this;
    }

    /**
     * Resizes this text both horizontally and vertically.
     * @param dw the amount by which to resize the width on each side
     * @param dw the amount by which to resize the height on each side
     */
    public Text grow(double dw, double dh)
    {
        xGrow += dw;
        yGrow += dh;
        Canvas.getInstance().repaint();
        return this;
    }

    /**
     * Sets the color for drawing this text.
     * @param newColor the new color
     */
    public Text setColor(Color newColor)
    {
        color = newColor;
        Canvas.getInstance().repaint();
        return this;
    }

    /**
     * Shows this text on the canvas.
     */
    public Text draw()
    {
        Canvas.getInstance().show(this);
        return this;
    }

    /**
        Undraws this text.
    */
    public Text undraw()
    {
        Canvas.getInstance().unshow(this);
        return this;
    }

    // new method added by Neato to support translating, changing pkgPluss.Text Objects
    public Text setText(String update)
	{
		label.setText(update);
		Canvas.getInstance().repaint();
        return this;
	}

    public String toString()
    {
        return "pkgPluss.Text[x=" + getX() + ",y=" + getY() + ",message=" + label.getText() + "]";
    }

    public void paintShape(Graphics2D g2)
    {
        if (color != null)
        {
            label.setForeground(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
            Dimension dim = label.getPreferredSize();
            if (dim.width > 0 && dim.height > 0)
            {
                label.setBounds(0, 0, dim.width, dim.height);
                g2.translate(getX(), getY());
                g2.scale(getWidth() / dim.width, getHeight() / dim.height);
                label.paint(g2);
            }
        }
    }
}
