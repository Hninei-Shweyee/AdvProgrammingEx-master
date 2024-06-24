package se233.chapter1.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se233.chapter1.model.item.BasedEquipment;
import se233.chapter1.Launcher;

import java.util.ArrayList;

import static se233.chapter1.controller.AllCustomHandler.onDragDetected;
import static se233.chapter1.controller.AllCustomHandler.onEquipdone;

public class InventoryPane extends ScrollPane {
    private ArrayList<BasedEquipment> equipmentArray;
    public InventoryPane(){    }

    //create pane with items
    private Pane getDetailsPane(){
        Pane inventoryInfoPane= new HBox(10);   //createPane
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25, 25, 25, 25));

        if (equipmentArray != null){    //add equipments to imageview
            ImageView[] imageViewList= new ImageView[equipmentArray.size()];
            for(int i=0; i<equipmentArray.size(); i++){
                imageViewList[i]= new ImageView();
                imageViewList[i].setImage(new Image(Launcher.class.getResource(equipmentArray.get(i).getImagepath()).toString()));

                //add DragFunctions for each item
                int finalI=i;
                imageViewList[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        onDragDetected(event, equipmentArray.get(finalI), imageViewList[finalI]);   //preDefined in AllCustomhandler class
                    }
                });
                imageViewList[i].setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        onEquipdone(event);
                    }
                });
            }
            inventoryInfoPane.getChildren().addAll(imageViewList);  //add imageview to the Pane
        }

        return inventoryInfoPane;
    }

    public void drawPane(ArrayList<BasedEquipment> equipmentArray){
        this.equipmentArray = equipmentArray;
        Pane inventoryInfo= getDetailsPane();
        this.setStyle("-fx-background-color:Red;");     //this == inventoryInfoPane
        this.setContent(inventoryInfo);
    }
}
