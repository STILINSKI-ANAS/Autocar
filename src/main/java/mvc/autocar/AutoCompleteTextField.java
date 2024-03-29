package mvc.autocar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.*;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a supplied list of entries.
 * @author Caleb Brinkman
 */
public class AutoCompleteTextField extends TextField
{
    /** The existing autocomplete entries. */
    private final SortedSet<String> entries;
    /** The popup used to select an entry. */
    private ContextMenu entriesPopup;

    /** Construct a new AutoCompleteTextField. */
    public AutoCompleteTextField() {
        super();
        entries = new TreeSet<>();
        String[] num = {"casablanca","fez","tangier","marrakesh","salé","meknes","rabat","oujda","kenitra","agadir",
                        "tetouan","temara","safi","mohammedia","khouribga","el jadida","beni mellal","nador",
                        "taza","settat","berrechid","khemisset","inezgane","ksar el kebir","larache","guelmim","khenifra",
                        "berkane","taourirt","bouskoura","fquih ben salah","oued zem","el kelaa des sraghna",
                        "sidi slimane","errachidia","guercif","oulad teima","ben guerir","tifelt","taroudant","sefrou",
                        "essaouira","fnideq","sidi kacem","tiznit","tan-tan","ouarzazate","souk el arbaa","youssoufia","lahraouyine",
                        "martil","ain harrouda","suq as-sabt awlad an-nama","skhirat","ouazzane","benslimane","al hoceima","beni ansar",
                        "m'diq","sidi bennour","midelt"};
        List<String> list = Arrays.asList(num);
        entries.addAll(list);
        entriesPopup = new ContextMenu();
        entriesPopup.setStyle("-fx-font-size:30px;-fx-font-family:System;");
        textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (getText().length() == 0)
                {
                    entriesPopup.hide();
                } else
                {
                    LinkedList<String> searchResult = new LinkedList<>();
                    searchResult.addAll(entries.subSet(getText().toLowerCase(), getText().toLowerCase() + Character.MAX_VALUE));
                    if (entries.size() > 0)
                    {
                        populatePopup(searchResult);
                        if (!entriesPopup.isShowing())
                        {
                            entriesPopup.show(AutoCompleteTextField.this, Side.BOTTOM, 0, 0);
                        }
                    } else
                    {
                        entriesPopup.hide();
                    }
                }
            }
        });

        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                entriesPopup.hide();
            }
        });

    }

    /**
     * Get the existing set of autocomplete entries.
     * @return The existing autocomplete entries.
     */
    public SortedSet<String> getEntries() { return entries; }

    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++)
        {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            entryLabel.setPrefWidth(400);
            entryLabel.setPrefHeight(50);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setGraphic(entryLabel);
            item.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setText(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);
    }
}