package sc.fiji.GraphEditor.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorConsole extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTextArea ta;
	private final TextAreaOutputStream taStream;
	private final PrintStream sOut;
	private final PrintStream sErr;

	public EditorConsole() {
		super(new BorderLayout());
		ta = new JTextArea("Welcome to SNT's Graph Editor");
		ta.setLineWrap(false);
		sOut = System.out;
		sErr = System.err;
		taStream = new TextAreaOutputStream(ta);
		// ta.setMargin(new Insets(10,10,10,10));
		ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, ta.getFont().getSize()));
		final JScrollPane pane = new JScrollPane(ta);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(pane);
	}

	public void setPreferredWidth(int width) {
		int cols = Math.max(1, width / 55);
		setPreferredSize(new Dimension(width, (getComponentCount() * 55 / cols) + 30));
		revalidate();
	}

	public void redirect() {
		System.setOut(taStream.getPrintStream());
		System.setErr(taStream.getPrintStream());
	}

	public void restore() {
		System.setOut(sOut);
		System.setErr(sErr);
	}

	public void restoreOut() {
		System.setOut(sOut);
	}

	public void restoreErr() {
		System.setErr(sErr);
	}

	public void clear() {
		taStream.clear();
	}
//
//	public void logAndRestore(final Object obj) {
//		redirect();
//		taStream.log(obj);
//		restore();
//	}
//
//	public void log(final Object obj) {
//		taStream.log(obj);
//	}
}
