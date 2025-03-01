package edu.ntnu.idi.idatt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
  private final int handSize = 5;
  private final DeckOfCards deck = new DeckOfCards();
  private HandOfCards currentHand;
  private final Text[] fillerText = new Text[]{
      new Text("Sum: ?"),
      new Text("Hearts: ?"),
      new Text("Flush: ?"),
      new Text("Queen of Spades: ?")
  };

  @Override
  public void start(Stage stage) {
    VBox root = new VBox(20);
    root.setAlignment(Pos.CENTER);
    root.setStyle("-fx-background-color: #eeeeee; -fx-padding: 20;");

    Scene scene = new Scene(root, 700, 500);

    Text title = createTitle("Play poker!");
    HBox cardContainer = createCardBox();
    HBox buttonContainer = createButtonBox();
    GridPane textGrid = createTextGrid();

    Button dealBtn = new Button("Deal hand");
    dealBtn.setOnAction(createDealBtnEventHandler(cardContainer, textGrid));
    Button checkBtn = new Button("Check hand");
    checkBtn.setOnAction(createCheckBtnEventHandler(textGrid));
    buttonContainer.getChildren().addAll(dealBtn, checkBtn);

    root.getChildren().addAll(title, cardContainer, buttonContainer, textGrid);

    stage.setTitle("Card game");
    stage.setScene(scene);
    stage.show();
  }

  private Text createTitle(String text) {
    Text title = new Text(text);
    title.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
    return title;
  }

  private HBox createCardBox() {
    HBox cardBox = new HBox(5);
    cardBox.setAlignment(Pos.CENTER);
    cardBox.setMinHeight(200);

    cardBox.setStyle("-fx-background-color: lightgreen;" +
        " -fx-padding: 20;" +
        " -fx-border-style: solid;" +
        "-fx-border-width: 2;" +
        "-fx-border-color: green");
    return cardBox;
  }

  private HBox createButtonBox() {
    HBox buttonContainer = new HBox(15);
    buttonContainer.setAlignment(Pos.CENTER);

    return buttonContainer;
  }

  private GridPane createTextGrid() {
    GridPane textGrid = new GridPane();
    textGrid.setHgap(10);
    textGrid.setVgap(10);
    textGrid.setAlignment(Pos.CENTER);

    fillGridPane(textGrid, fillerText);
    return textGrid;
  }

  private void fillGridPane(GridPane gridPane, Text[] texts) {
    gridPane.getChildren().clear();
    gridPane.add(texts[0], 0, 0);
    gridPane.add(texts[1], 1, 0);
    gridPane.add(texts[2], 0, 1);
    gridPane.add(texts[3], 1, 1);
  }

  private void addCards(HBox cardBox, HandOfCards hand){
    for (PlayingCard card : hand){
      cardBox.getChildren().add(createCard(card));
    }
  }

  private StackPane createCard(PlayingCard playingCard) {
    StackPane ret = new StackPane();

    Rectangle card = new Rectangle(90, 130);
    card.setArcWidth(10);
    card.setArcHeight(5);
    card.setFill(Color.WHITE);
    card.setStrokeWidth(2);

    Text label = createTitle(playingCard.getAsString());

    if (playingCard.getSuit() == 'H' || playingCard.getSuit() == 'D'){
      card.setStroke(Color.RED);
      label.setStyle("-fx-text-fill: red");
    } else {
      card.setStroke(Color.BLACK);
    }

    ret.getChildren().addAll(card, label);
    return ret;
  }

  private EventHandler<ActionEvent> createDealBtnEventHandler(HBox cardContainer, GridPane textGrid) {
    return actionEvent -> {
      currentHand = deck.dealHand(handSize);
      cardContainer.getChildren().clear();
      addCards(cardContainer, currentHand);

      fillGridPane(textGrid, fillerText);
    };
  }

  private EventHandler<ActionEvent> createCheckBtnEventHandler(GridPane textGrid) {
    return actionEvent -> {
      if (currentHand == null){
        return;
      }

      Text[] newTexts = new Text[4];
      for (int i = 0; i < 4; i++) {
        String text = switch (i) {
          case 0 -> String.valueOf(currentHand.getSum());
          case 1 -> currentHand.getHearts().getAsString();
          case 2 -> currentHand.isFlush() ? "Yes" : "No";
          case 3 -> currentHand.hasQueenOfSpades() ? "Yes" : "No";
          default -> "";
        };
        newTexts[i] = new Text(fillerText[i].getText().replace("?", text));
      }

      fillGridPane(textGrid, newTexts);
    };
  }

  public static void main(String[] args) {
    launch(args);
  }
}