package contoller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Livro;
import model.DAO.LivroDAO;
import model.bean.Vendedor;

public class LivroController {

    private Livro vendedorSelecionado;
    private List<Livro> tabelaDeVendedores;
    private LivroDAO lDAO;
    private Object l;

    public LivroController() {
        lDAO = new LivroDAO();
    }

    public void listarTodos(DefaultTableModel modeloTabela) {
        modeloTabela.setNumRows(0);
        List<Livro> listaLivros = lDAO.buscarTodos();

        for (Livro l : listaLivros) {
            modeloTabela.addRow(new Object[]{l.getId(), l.getISBN(),
                l.getTitulo(), l.getAutor(),l.getPaginas(),l.getPreco(),
                l.getCategoria(), l.isStatus() ? "1 - Ativo" : "2 - Inativo"});
        }
    }

    public void listarPorId(DefaultTableModel modeloTabela, int id) {
        modeloTabela.setNumRows(0);
         Livro l = lDAO.buscarPorId(id);

        modeloTabela.addRow(new Object[]{l.getId(), l.getISBN(),
            l.getTitulo(), l.getAutor(), l.isStatus(), l.getPreco(),
            l.getCategoria(), l.getPaginas() });
    }
    
    public void salvar(DefaultTableModel modeloTabela, Livro livro, boolean novo ) {
        if( novo ) {
            lDAO.inserir(livro);
        } else {
            lDAO.atualizar(livro);
        }
        this.listarTodos(modeloTabela);
    }
    
    public void excluir(DefaultTableModel modeloTabela, Livro livro ) {
        System.out.println("Excluindo Livro No.: " + livro.getId());
        if( livro.getId() != 0 ) {
            lDAO.excluir(livro.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir as informações.\nLivro não localizado.", "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
        }
        this.listarTodos(modeloTabela);
    }

    public void listarPorISBN(DefaultTableModel tabelaModelo, int ISBN) {
         modeloTabela.setNumRows(0);
        Livro livroBuscado = lDAO.buscarPorISBN(ISBN);

        modeloTabela.addRow(new Object[]{livroBuscado.getId(), livroBuscado.getISBN(),
            livroBuscado.getTitulo(), livroBuscado.getAutor(), livroBuscado.isStatus() ? "1 - Ativo" : "2 - Inativo"});
    }

    public void excluir(DefaultTableModel tabelaModelo, view.TelaLivro lSelecionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void salvar(DefaultTableModel tabelaModelo, view.TelaLivro lSelecionado, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

