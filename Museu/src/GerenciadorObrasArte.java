import java.util.ArrayList;

public abstract class GerenciadorObrasArte {
    
    private static ArrayList<ObraArte> listaObras = new ArrayList<>();

    public static void salvarObra(ObraArte obra){
        listaObras.add(obra);
    }

    public static ArrayList<ObraArte> getListaObras(){
        return listaObras;
    }

    public static ObraArte buscarObra(String titulo) throws Exception{
        for(ObraArte temp : listaObras){
            if(temp.getTitulo().equals(titulo)){
                return temp;
            }
        }
        throw new Exception("\nObra de arte não localizada");
    }

    public static void excluirObra(ObraArte obra){
        listaObras.remove(obra);
    }

}
