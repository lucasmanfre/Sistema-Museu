import java.util.ArrayList;

public class Sistema {

    private static void exibirMenu(){

        System.out.println("\nMuseu System");
        System.out.println("1 - Cadastrar Obra de Arte");
        System.out.println("2 - Buscar Obra");
        System.out.println("3 - Atualizar Dados");
        System.out.println("4 - Apagar Obra de Arte");
        System.out.println("5 - Listar todas as Obras");
        System.out.println("0 - Sair");

    }

    private static void cadastrarObra(){
        System.out.println("\nNova Obra de Arte");
        String titulo = Console.lerString("Titulo: ");
        String artista = Console.lerString("Artista: ");
        int anoCriacao = Console.lerInt("Ano de Criação: ");
        String tipoObra = Console.lerString("Tipo de Obra: ");
        String localizacao = Console.lerString("Localização do Museu: ");

        ObraArte obra = new ObraArte(titulo, artista, anoCriacao, tipoObra, localizacao);

        GerenciadorObrasArte.salvarObra(obra);

        try{
            ObraPersistencia.salvarNoArquivo();
            System.out.println("\nObra de arte salva com sucesso");
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private static void buscarObra(){
        System.out.println("\nBusca de obra: ");
        String titulo = Console.lerString("Informe o titulo: ");

        try {
            ObraArte busca = GerenciadorObrasArte.buscarObra(titulo);

            System.out.println("\nObra localizada: " + busca.exibirDados());

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void apagarObra(){
        System.out.println("\nApagar obra: ");
        String titulo = Console.lerString("Informe o titulo: ");

        try {
            ObraArte busca = GerenciadorObrasArte.buscarObra(titulo);

            GerenciadorObrasArte.excluirObra(busca);

            ObraPersistencia.salvarNoArquivo();

            System.out.println("\nObra de arte " + busca.getTitulo() + " excluido com sucesso!");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void atualizarObra(){
        System.out.println("\nAtualizar obra: ");
        String titulo = Console.lerString("Informe o titulo: ");

        try {
            ObraArte busca = GerenciadorObrasArte.buscarObra(titulo);

            System.out.println("\nInforme os novos dados:");
            titulo = Console.lerString("Titulo: ");
            String artista = Console.lerString("Artista: ");
            int anoCriacao = Console.lerInt("Ano de Criação: ");
            String tipoObra = Console.lerString("Tipo de Obra: ");
            String localizacao = Console.lerString("Localização do Museu: ");

            busca.setTitulo(titulo);
            busca.setArtista(artista);
            busca.setAnoCriacao(anoCriacao);
            busca.setTipoObra(tipoObra);
            busca.setLocalizacao(localizacao);

            ObraPersistencia.salvarNoArquivo();

            System.out.println("\nObra de arte " + busca.getTitulo() + " alterada com sucesso!");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void listarObras(){
        ArrayList<ObraArte> listaObras = GerenciadorObrasArte.getListaObras();
        
        if(listaObras.isEmpty()){
            System.out.println("\nNão há obras cadastradas");
            return;
        }

        System.out.println("\nObras Cadastradas:");
        for(ObraArte temp : listaObras){
            System.out.println(temp.exibirDados());
        }
    }

    private static void verificarOp(int op){
        switch (op) {
            case 1:
                cadastrarObra();
                break;

            case 2:
                buscarObra();
                break;

            case 3:
                atualizarObra();
                break;

            case 4:
                apagarObra();
                break;

            case 5:
                listarObras();
                break;

            case 0:
                System.out.println("\nFinalizando o programa...\n");
                System.exit(0);
                break;
        
            default:
                System.out.println("Opção inválida, tente novamente...");
                break;
        }
    }

    public static void executar(){
        try{
            ObraPersistencia.criarArquivoSeNaoExistir();
            ObraPersistencia.lerDoArquivo();

        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

        while (true) {
            exibirMenu();
            int op = Console.lerInt("Informe uma opção: ");
            verificarOp(op);
        }
    }

}
