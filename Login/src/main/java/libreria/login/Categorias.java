package libreria.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Categorias extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton addButton, editButton, deleteButton, openButton;

    public Categorias() {
        setTitle("Categorias");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Columna1");
        
        
        tableModel.addColumn("Columna2");
        tableModel.addColumn("Columna3");
        // Agrega más columnas según tu archivo CSV

        // Crear la tabla con el modelo
        table = new JTable(tableModel);

        // Crear botones para añadir, editar, eliminar y abrir archivo CSV
        addButton = new JButton("Añadir");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Eliminar");
        openButton = new JButton("Abrir CSV");
        JButton exportTxtButton = new JButton("Exportar TXT");

        // Agregar los botones a un panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(openButton);
        buttonPanel.add(exportTxtButton);

        // Agregar un listener para el botón de añadir
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agregar una nueva fila vacía a la tabla
                tableModel.addRow(new Object[tableModel.getColumnCount()]);
            }
        });

        // Agregar un listener para el botón de editar
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada
                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {
                    // Mostrar un cuadro de diálogo para editar los valores
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        String value = JOptionPane.showInputDialog("Editar valor para " + table.getColumnName(i));
                        table.setValueAt(value, selectedRow, i);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
                }
            }
        });

        // Agregar un listener para el botón de eliminar
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada
                int selectedRow = table.getSelectedRow();

                if (selectedRow >= 0) {
                    // Eliminar la fila seleccionada
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
                }
            }
        });

        // Agregar un listener para el botón de abrir CSV
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("D:\\netbeans v17\\NetBeans-17\\proyectos\\Login\\src\\main\\java\\libreria\\login\\Categorias.csv", "csv"));

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        // Limpiar la tabla antes de cargar un nuevo archivo CSV
                        tableModel.setRowCount(0);

                        // Leer el archivo CSV y cargar los datos en la tabla
                        BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] data = line.split(";");
                            tableModel.addRow(data);
                        }

                        reader.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al abrir el archivo CSV.");
                    }
                }
            }
        });
        
        // Agregar un listener para el botón de exportar TXT
exportTxtButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt"));

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtener la ruta del archivo seleccionado
                File selectedFile = fileChooser.getSelectedFile();

                // Crear un escritor de archivos para guardar los datos en el archivo de texto (.txt)
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

                // Obtener el número de columnas y filas en la tabla
                int numRows = tableModel.getRowCount();
                int numCols = tableModel.getColumnCount();

                // Escribir las cabeceras de columna en el archivo de texto (.txt)
                for (int col = 0; col < numCols; col++) {
                    writer.write(tableModel.getColumnName(col));
                    if (col < numCols - 1) {
                        writer.write("\t"); // Usar tabulación como delimitador en lugar de ","
                    }
                }
                writer.newLine();

                // Escribir los datos de la tabla en el archivo de texto (.txt)
                for (int row = 0; row < numRows; row++) {
                    for (int col = 0; col < numCols; col++) {
                        Object value = tableModel.getValueAt(row, col);
                        writer.write(value.toString());
                        if (col < numCols - 1) {
                            writer.write("\t"); // Usar tabulación como delimitador en lugar de ","
                        }
                    }
                    writer.newLine();
                }

                writer.close();

                JOptionPane.showMessageDialog(null, "Archivo TXT exportado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al exportar el archivo TXT.");
            }
        }
    }
});

        // Agregar la tabla y el panel de botones al frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Categorias().setVisible(true);
            }
        });
    }
}

