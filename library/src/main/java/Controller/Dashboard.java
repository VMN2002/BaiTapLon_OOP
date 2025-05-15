package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class Dashboard {

    @FXML
    private StackPane contentArea;

    @FXML
    public void showDocuments() throws IOException {
        loadUI("/com/example/library/view/Document.fxml");
    }

    @FXML
    public void showUsers() throws IOException {
        loadUI("/com/example/library/view/View.fxml");
    }

    @FXML
    public void showBorrowing() throws IOException {
        loadUI("/com/example/library/view/Borrow.fxml");
    }

    private void loadUI(String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
    }
}

