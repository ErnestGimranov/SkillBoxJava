import java.util.HashMap;

public class ColorDictionary {
    public static String getColorByHex(String hex){
        HashMap<String, String> colorDictionary = new HashMap<>();
        colorDictionary.put("background:#EF161E", "red");
        colorDictionary.put("background:#2DBE2C", "green");
        colorDictionary.put("background:#0078BE", "blue");
        colorDictionary.put("background:#00BFFF", "light blue");
        colorDictionary.put("background:#8D5B2D", "brown");
        colorDictionary.put("background:#ED9121", "orange");
        colorDictionary.put("background:#800080", "purple");
        colorDictionary.put("background:#FFD702", "yellow");
        colorDictionary.put("background:#999999", "grey");
        colorDictionary.put("background:#99CC00", "light green");
        colorDictionary.put("background:#82C0C0", "light gray blue");
        colorDictionary.put("background:#A1B3D4", "Very light blue");
        colorDictionary.put("background:#DE64A1", "pink");
        return colorDictionary.get(hex);
    }
}
