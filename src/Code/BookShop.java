import java.util.*;

public class BookShop {
    private final Map<String, Novel> novelMaps;
    private final Iterator<Novel> it;

    public BookShop(final List<Novel> novels) {
        novelMaps = new HashMap<>();
        it = novels.iterator();

        for(Novel n: novels){
            novelMaps.put(n.getTitle(), n);
        }

        iteratorNovels();
    }

    public void iteratorNovels() {
        while(it.hasNext()){
            final Novel n;

            n = it.next();

            System.out.println(n);

            // remove title contain "the"
            final String title;
            title = n.getTitle().toLowerCase();

            if(title.contains("the")){
                it.remove();
            }
        }
    }
}
