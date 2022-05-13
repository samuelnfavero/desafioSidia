import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public int execute(int[] rooms, int[] rent, int[] requirement, int[] budget) {


        List<Integer> roomsList = convertToList(rooms);
        List<Integer> rentList = convertToList(rent);
        List<Integer> requirementList = convertToList(requirement);
        List<Integer> budgetList = convertToList(budget);
        int valor = 0;

        for (int i = 0; i < requirementList.size(); i++) {

            int melhorQuarto = -1;

            List posicoes = new ArrayList<Integer>();
            for (int j = 0; j < roomsList.size(); j++) {

                if (roomsList.get(j) >= requirementList.get(i)) {

                    posicoes.add(j);
                }
            }

            for (int k = 0; k < posicoes.size() ; k++) {

                int rente =rentList.get((int) posicoes.get(k));
                int budgete = budgetList.get(i);
                if (rentList.get((int) posicoes.get(k)) > budgetList.get(i)) {



                    posicoes.remove(Integer.valueOf((int) posicoes.get(k)));
                    k--;
                }
            }

            if (posicoes.size() != 0) {

                int valorMaisBaixo = rentList.get((int) posicoes.get(0));

                for (int l = 0; l < posicoes.size(); l++) {
                    if (rentList.get((int) posicoes.get(l)) < valorMaisBaixo) {
                        valorMaisBaixo = rentList.get((int) posicoes.get(l));
                    }
                }

                for (int p = 0; p < posicoes.size(); p++) {
                    if (rentList.get((int) posicoes.get(p)) != valorMaisBaixo) {
                        posicoes.remove(Integer.valueOf((int) posicoes.get(p)));
                        p--;
                    }
                }

                int maiorNumDeQuartos = roomsList.get((int) posicoes.get(0));


                for (int t = 0; t < posicoes.size(); t++) {
                    if (roomsList.get((int) posicoes.get(t)) >= maiorNumDeQuartos) {
                        maiorNumDeQuartos = roomsList.get((int) posicoes.get(t));
                        melhorQuarto = (int) posicoes.get(t);
                    }
                }

                if (melhorQuarto != -1) {
                    System.out.printf("O cliente %s ficou com o quarto %s\n", i, melhorQuarto);
                    valor += rentList.get(melhorQuarto);
                    roomsList.remove(melhorQuarto);
                    rentList.remove(melhorQuarto);
                }
            }

            if(melhorQuarto == -1) System.out.printf("Nenhum quarto satisfez o cliente %s\n", i);
        }
        return valor;
    }

    public ArrayList<Integer> convertToList(int[] array){
        ArrayList list = new ArrayList<Integer>();
        for(int i = 0; i < array.length; i++){
            list.add(array[i]);
        }
        return list;
    }
}
