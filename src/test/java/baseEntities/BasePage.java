package baseEntities;


public abstract class BasePage {

    protected abstract void openPage(String endpoint);

    public BasePage(boolean openPageByUrl, String endpoint) {

        if (openPageByUrl) {
            openPage(endpoint);
        }
    }
}
