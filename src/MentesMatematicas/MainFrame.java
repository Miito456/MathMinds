package MentesMatematicas;

import MentesMatematicas.Quimica.EcuacionNernstPanel;
import MentesMatematicas.Fisica.TiroParabolicoPanel;
import MentesMatematicas.Estadistica.OrdenarPanel;
import MentesMatematicas.Estadistica.PermYCombPanel;
import MentesMatematicas.Estadistica.EstadisticaPanel;
import MentesMatematicas.Matrices.OperacionesPanel;
import MentesMatematicas.Matrices.ResolverPanel;
import MentesMatematicas.Algebra.EcuacionesPanel;
import MentesMatematicas.Fisica.MovimientoAPanel;
import MentesMatematicas.Formulas.FuncionGammaPanel;
import MentesMatematicas.Formulas.ZetaRiemannPanel;
import MentesMatematicas.Formulas.TeoremaDeBayesPanel;
import MentesMatematicas.Formulas.EntropiaShannonPanel;
import MentesMatematicas.Formulas.FuncionBesselPanel;
import MentesMatematicas.Formulas.IntegralDeFresnelPanel;
import MentesMatematicas.Formulas.FormulaGeneralPanel;
import MentesMatematicas.Quimica.BalanceoPanel;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel contentPane;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        JPanel welcomePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        JLabel welcomeLabel = new JLabel("Bienvenido a MathMinds");
        welcomeLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 46));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        JLabel logoLabel = new JLabel(new ImageIcon("Images/loge.png"));
        welcomePanel.add(logoLabel, gbc);

        contentPane = new JPanel(new BorderLayout());
        contentPane.add(welcomePanel, BorderLayout.CENTER);
        getContentPane().add(contentPane, BorderLayout.CENTER);

        Font font = new Font("Yu Gothic UI", Font.PLAIN, 14);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);

        addSideMenu();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addSideMenu() {
        JPanel sideMenuPanel = new JPanel(new BorderLayout());
        sideMenuPanel.setPreferredSize(new Dimension(220, getHeight()));
        sideMenuPanel.setBackground(Color.decode("#2D142C"));

        JLabel logoLabel = new JLabel(new ImageIcon("Images/logo.png"));
        sideMenuPanel.add(logoLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel("MathMinds");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sideMenuPanel.add(nameLabel, BorderLayout.CENTER);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu");
        DefaultMutableTreeNode matematica = new DefaultMutableTreeNode("Matematica elemental");
        matematica.add(new DefaultMutableTreeNode("Calculadora simple"));
//        DefaultMutableTreeNode algebra = new DefaultMutableTreeNode("Algebra");
//        algebra.add(new DefaultMutableTreeNode("Ecuaciones"));
//        algebra.add(new DefaultMutableTreeNode("Polinomios"));
//        algebra.add(new DefaultMutableTreeNode("Sistema de ecuaciones"));
        DefaultMutableTreeNode formulas = new DefaultMutableTreeNode("Formulas Matemáticas");
        formulas.add(new DefaultMutableTreeNode("Formula general"));
       // formulas.add(new DefaultMutableTreeNode("Zeta de Riemann"));
        formulas.add(new DefaultMutableTreeNode("Teorema de Bayes"));
        formulas.add(new DefaultMutableTreeNode("Integral de Fresnel"));
        //formulas.add(new DefaultMutableTreeNode("Funcion Gamma"));
        formulas.add(new DefaultMutableTreeNode("Funcion de Bessel"));
        DefaultMutableTreeNode algebralineal = new DefaultMutableTreeNode("Matrices");
        algebralineal.add(new DefaultMutableTreeNode("Resolver"));
        algebralineal.add(new DefaultMutableTreeNode("Operaciones"));
        DefaultMutableTreeNode fisica = new DefaultMutableTreeNode("Fisica");
        fisica.add(new DefaultMutableTreeNode("Tiro parabolico"));
        fisica.add(new DefaultMutableTreeNode("Movimiento armonico"));
        DefaultMutableTreeNode estadistica = new DefaultMutableTreeNode("Estadistica");
        estadistica.add(new DefaultMutableTreeNode("Calculos estadisticos"));
        estadistica.add(new DefaultMutableTreeNode("Ordenar"));
        estadistica.add(new DefaultMutableTreeNode("Perm y Comb"));
        DefaultMutableTreeNode quimica = new DefaultMutableTreeNode("Quimica");
        quimica.add(new DefaultMutableTreeNode("Balanceo"));
        quimica.add(new DefaultMutableTreeNode("Ecuacion de Nernst"));
        root.add(matematica);
        // root.add(algebra);
        root.add(fisica); 
        root.add(algebralineal);
        root.add(estadistica);
        root.add(quimica);
        root.add(formulas);
        JTree tree = new JTree(root);
        tree.setBackground(Color.decode("#2D142C"));
        tree.setForeground(Color.WHITE);
        tree.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
        tree.setRootVisible(false);
        tree.setToggleClickCount(1);

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                String nodeText = (String) node.getUserObject();
                label.setIcon(new ImageIcon("Images/logo.png"));
                label.setText(nodeText);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                label.setBackground(Color.decode("#2D142C"));
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
                return label;
            }
        };
        renderer.setLeafIcon(null);
        tree.setCellRenderer(renderer);

        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node != null && node.isLeaf()) {
                String selectedOption = (String) node.getUserObject();
                if (selectedOption.equals("Formula general")) {
                    contentPane.removeAll();
                    FormulaGeneralPanel formulaPanel = new FormulaGeneralPanel();
                    contentPane.add(formulaPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Calculadora simple")) {
                    contentPane.removeAll();
                    CalculatorPanel calculatorPanel = new CalculatorPanel();
                    contentPane.add(calculatorPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Ecuaciones")) {
                    contentPane.removeAll();
                    EcuacionesPanel ecuaciones = new EcuacionesPanel();
                    contentPane.add(ecuaciones, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Zeta de Riemann")) {
                    contentPane.removeAll();
                    ZetaRiemannPanel zetadeRiemann = new ZetaRiemannPanel();
                    contentPane.add(zetadeRiemann, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Teorema de Bayes")) {
                    contentPane.removeAll();
                    TeoremaDeBayesPanel teoremaBayes = new TeoremaDeBayesPanel();
                    contentPane.add(teoremaBayes, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Entropía de Shannon")) {
                    contentPane.removeAll();
                    EntropiaShannonPanel entropiaShannon = new EntropiaShannonPanel();
                    contentPane.add(entropiaShannon, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Integral de Fresnel")) {
                    contentPane.removeAll();
                    IntegralDeFresnelPanel fresnelPanel = new IntegralDeFresnelPanel();
                    contentPane.add(fresnelPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Funcion Gamma")) {
                    contentPane.removeAll();
                    FuncionGammaPanel gammaPanel = new FuncionGammaPanel();
                    contentPane.add(gammaPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Funcion de Bessel")) {
                    contentPane.removeAll();
                    FuncionBesselPanel besselPanel = new FuncionBesselPanel();
                    contentPane.add(besselPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Resolver")) {
                    contentPane.removeAll();
                    ResolverPanel resolverPanel = new ResolverPanel();
                    contentPane.add(resolverPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Operaciones")) {
                    contentPane.removeAll();
                    OperacionesPanel operacionesPanel = new OperacionesPanel();
                    contentPane.add(operacionesPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();

                } else if (selectedOption.equals("Tiro parabolico")) {
                    contentPane.removeAll();
                    TiroParabolicoPanel tiroParabolicoPanel = new TiroParabolicoPanel();
                    contentPane.add(tiroParabolicoPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Ordenar")) {
                    contentPane.removeAll();
                    OrdenarPanel ordenarPanel = new OrdenarPanel();
                    contentPane.add(ordenarPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Calculos estadisticos")) {
                    contentPane.removeAll();
                    EstadisticaPanel estadisticaPanel = new EstadisticaPanel();
                    contentPane.add(estadisticaPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Perm y Comb")) {
                    contentPane.removeAll();
                    PermYCombPanel permYCombPanel = new PermYCombPanel();
                    contentPane.add(permYCombPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Balanceo")) {
                    contentPane.removeAll();
                    BalanceoPanel balanceoPanel = new BalanceoPanel();
                    contentPane.add(balanceoPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Movimiento armonico")) {
                    contentPane.removeAll();
                    MovimientoAPanel movimientoPanel = new MovimientoAPanel();
                    contentPane.add(movimientoPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Ecuacion de Nernst")) {
                    contentPane.removeAll();
                    EcuacionNernstPanel ecuacionNernstPanel = new EcuacionNernstPanel();
                    contentPane.add(ecuacionNernstPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else if (selectedOption.equals("Ecuaciones")) {
                    contentPane.removeAll();
                    // EquationsPanel equationsPanel = new EquationsPanel();
                    // contentPane.add(equationsPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                } else {
                    contentPane.removeAll();
                    MainPanel mainPanel = new MainPanel();
                    JLabel label = new JLabel("Seleccionaste " + selectedOption);
                    label.setForeground(Color.WHITE);
                    mainPanel.add(label);
                    contentPane.add(mainPanel, BorderLayout.CENTER);
                    contentPane.revalidate();
                    contentPane.repaint();
                }
            }
        }
        );

        JScrollPane scrollPane = new JScrollPane(tree);

        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        sideMenuPanel.add(scrollPane, BorderLayout.CENTER);

        JButton exitButton = new JButton("Salir");

        exitButton.setForeground(Color.WHITE);

        exitButton.setBackground(Color.decode("#2D142C"));
        exitButton.setFont(
                new Font("Yu Gothic UI", Font.PLAIN, 14));
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exitButton.setFocusPainted(
                false);
        exitButton.addActionListener(e
                -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        );
        sideMenuPanel.add(exitButton, BorderLayout.SOUTH);

        getContentPane()
                .add(sideMenuPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
