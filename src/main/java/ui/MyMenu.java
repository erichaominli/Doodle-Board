package ui;

import logic.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyMenu extends JMenuBar {
    private Graph graph;
    private View view;

    public MyMenu(Graph g, View v) {
        graph = g;
        view = v;
        JMenu FileMenu = new JMenu("File");
        JMenuItem newfile = new JMenuItem("New");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        newfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!graph.getDelete().empty() || !graph.getExist().empty()) {
                    graph.getExist().clear();
                    graph.getDelete().clear();
                }
            }
        });

        save.addActionListener(e -> {
            int retval = JOptionPane.showConfirmDialog(null, " Save file");
            if (retval == JOptionPane.YES_OPTION) {
                JFileChooser save_file = new JFileChooser();
                save_file.setDialogTitle("Save Your File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".pat", ".pat");
                save_file.setFileFilter(filter);
                if (save_file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    String fileName = save_file.getSelectedFile().getAbsolutePath() + ".pat";

                    try {
                        FileOutputStream file = new FileOutputStream(fileName);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(graph);
                        out.close();
                        file.close();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser load_file = new JFileChooser();
                load_file.setDialogTitle("Load your file");
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".pat", ".pat");
                load_file.setFileFilter(filter);
                if (load_file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    String fileName = load_file.getSelectedFile().getAbsolutePath();

                    try{
                        FileInputStream file = new FileInputStream(fileName);
                        ObjectInputStream in = new ObjectInputStream(file);
                        Graph g = (Graph) in.readObject();
                        in.close();
                        file.close();
                    }
                    catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        FileMenu.add(newfile);
        FileMenu.add(load);
        FileMenu.add(save);
        FileMenu.add(exit);

        this.add(FileMenu);

    }
}

