import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int execute(int[] rooms, int[] rent, int[] requirement, int[] budget) {


        List<Integer> roomsList = convertToList(rooms);
        List<Integer> rentList = convertToList(rent);
        List<Integer> requirementList = convertToList(requirement);
        List<Integer> budgetList = convertToList(budget);
        int valor = 0;

        for (int i = 0; i < requirementList.size(); i++) {

            int bestFlat = -1;

            List<Integer> roomsPosition = getFlatsWithEnoughtRooms(roomsList, requirementList, i);
            removeRoomsWithHigherPrice(roomsPosition,rentList, budgetList, i);

            if (roomsPosition.size() != 0) {

                int lowerPrice = getTheLowerPrice(roomsPosition, rentList);
                chooseTheLowestPriceRooms(roomsPosition, rentList,lowerPrice);

                int flatWithTheHighestNumberOfRooms = roomsList.get(roomsPosition.get(0));
                bestFlat = chooseTheFlatWithTheHighestNumberOfRoomsWithTheBestPrice(roomsPosition, roomsList, flatWithTheHighestNumberOfRooms, bestFlat);

                if (bestFlat != -1) {
                    System.out.printf("O cliente %s ficou com o quarto %s\n", i, bestFlat);
                    valor += rentList.get(bestFlat);
                    roomsList.remove(bestFlat);
                    rentList.remove(bestFlat);
                }
            }

            if(bestFlat == -1) System.out.printf("Nenhum quarto satisfez o cliente %s\n", i);
        }
        return valor;
    }

    private List<Integer> convertToList(int[] array){
        List<Integer> list = new ArrayList<Integer>();
        for (int j : array) {
            list.add(j);
        }
        return list;
    }

    private List<Integer> getFlatsWithEnoughtRooms(List<Integer> roomsList, List<Integer> requirementList, Integer clientNumber){
        List<Integer> roomsPosition = new ArrayList<Integer>();
        for (int i = 0; i < roomsList.size(); i++) {

            if (roomsList.get(i) >= requirementList.get(clientNumber)) {

                roomsPosition.add(i);
            }
        }
        return  roomsPosition;
    }

    private void removeRoomsWithHigherPrice(List<Integer> roomPosition, List<Integer> rentList, List<Integer> budgetList, Integer clientNumber){
        for (int k = 0; k < roomPosition.size() ; k++)
            if (rentList.get((int) roomPosition.get(k)) > budgetList.get(clientNumber)) {
                roomPosition.remove(Integer.valueOf((int) roomPosition.get(k)));
                k--;
            }
    }

    private int getTheLowerPrice(List<Integer> positions, List<Integer> rentList){
        int menorValor = rentList.get((int) positions.get(0));

        for (Integer p : positions) {
            if (rentList.get((int) p) < menorValor) {
                menorValor = rentList.get((int) p);
            }
        }
        return menorValor;
    }

    private void chooseTheLowestPriceRooms(List<Integer> roomPosition, List<Integer> rentList, int lowerPrice){
        for (int p = 0; p < roomPosition.size(); p++) {
            if (rentList.get((int) roomPosition.get(p)) != lowerPrice) {
                roomPosition.remove(Integer.valueOf((int) roomPosition.get(p)));
                p--;
            }
        }
    }

    private int chooseTheFlatWithTheHighestNumberOfRoomsWithTheBestPrice(List<Integer> roomsPosition, List<Integer> roomsList,
                                                                         int flatWithTheHighestNumberOfRooms, int melhorQuarto){
        for (Integer room : roomsPosition) {
            if (roomsList.get(room) >= flatWithTheHighestNumberOfRooms) {
                flatWithTheHighestNumberOfRooms = roomsList.get(room);
                melhorQuarto = room;
            }
        }
        return melhorQuarto;
    }
    private void removeNotEnoughtRooms(){}
}
