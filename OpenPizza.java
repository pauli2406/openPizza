import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class OpenPizza {
    public static void main(String[] args) throws IOException {

        File folder = new File("./inputs/");
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;

        for (File file : listOfFiles) {
            System.out.println("--------------------FILE " + file.getName());
            if (file.isFile()) {
                List<Client> clients = new ArrayList<>();
                List<String> content = new ArrayList();
                try {
                    Scanner myReader = new Scanner(file);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        content.add(data);
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                for (int i = 0; i < content.size(); i=i+2) {
                    List<String> likeable = new LinkedList<>(Arrays.asList(content.get(i).split(" ")));
                    int amountLikeable = Integer.parseInt(likeable.get(0));
                    likeable.remove(0);
                    List<String> notLikeable = null;
                    int amountNotLikeable = 0;
                    if (content.size() > i + 1) {
                        notLikeable = new LinkedList<>(Arrays.asList(content.get(i + 1).split(" ")));
                        amountNotLikeable = Integer.parseInt(notLikeable.get(0));
                        notLikeable.remove(0);
                    }

                    List<String> notLikedIngredient = new ArrayList<>();

                    List<String> likedIngredient = new ArrayList<>(likeable);

                    if (notLikeable != null) {
                        notLikedIngredient.addAll(notLikeable);
                    }

                    Client client = new Client(amountLikeable, amountNotLikeable, likedIngredient, notLikedIngredient);
                    clients.add(client);
                }
                System.out.println(clients);
                List<String> dislikedIngredientsList = new ArrayList<>();
                for (int i = 0; i < clients.size(); i++) {
                    dislikedIngredientsList.addAll(clients.get(i).unlikeable);
                }
                Set<String> dislikedIngredients = new HashSet<String>(dislikedIngredientsList);
                List<String> listOfDislikedIngredients = new ArrayList<>(dislikedIngredients);
                System.out.println("Disliked Ingredients");
                System.out.println(dislikedIngredients);

                for (int i = 0; i < dislikedIngredients.size(); i++) {
                    for (int j = 0; j < clients.size(); j++) {
                        boolean containsUnlikedIngredient = !clients.get(j).likeable.contains(listOfDislikedIngredients.get(i));
                    }
                }
            }
        }
    }
}



class Client {
    int amountOfIngredientsLikeable;
    int amountOfIngredientsNotLikeable;
    List<String> likeable;
    List<String> unlikeable;

    public Client(int amountOfIngredientsLikeable, int amountOfIngredientsNotLikeable, List<String> likeable, List<String> unlikeable) {
        this.amountOfIngredientsLikeable = amountOfIngredientsLikeable;
        this.amountOfIngredientsNotLikeable = amountOfIngredientsNotLikeable;
        this.likeable = likeable;
        this.unlikeable = unlikeable;
    }

    public int getAmountOfIngredientsNotLikeable() {
        return amountOfIngredientsNotLikeable;
    }

    public void setAmountOfIngredientsNotLikeable(int amountOfIngredientsNotLikeable) {
        this.amountOfIngredientsNotLikeable = amountOfIngredientsNotLikeable;
    }

    public List<String> getLikeable() {
        return likeable;
    }

    public void setLikeable(List<String> likeable) {
        this.likeable = likeable;
    }

    public List<String> getUnlikeable() {
        return unlikeable;
    }

    public void setUnlikeable(List<String> unlikeable) {
        this.unlikeable = unlikeable;
    }

    public int getAmountOfIngredientsLikeable() {
        return amountOfIngredientsLikeable;
    }

    public void setAmountOfIngredientsLikeable(int amountOfIngredientsLikeable) {
        this.amountOfIngredientsLikeable = amountOfIngredientsLikeable;
    }

    @Override
    public String toString() {
        return "Client{" +
                "amountOfIngredientsLikeable=" + amountOfIngredientsLikeable +
                ", amountOfIngredientsNotLikeable=" + amountOfIngredientsNotLikeable +
                ", likeable=" + likeable.toString() +
                ", unlikeable=" + unlikeable.toString() +
                '}';
    }
}