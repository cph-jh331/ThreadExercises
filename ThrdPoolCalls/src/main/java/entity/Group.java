package entity;

public class Group {

    private String authors;
    private String klasse;
    private String group;

    public Group()
    {
    }

    public Group(String authors, String klasse, String group)
    {
        this.authors = authors;
        this.klasse = klasse;
        this.group = group;
    }

    public String getAuthors()
    {
        return authors;
    }

    public void setAuthors(String authors)
    {
        this.authors = authors;
    }

    public String getKlasse()
    {
        return klasse;
    }

    public void setKlasse(String klasse)
    {
        this.klasse = klasse;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "Group{" + "authors=" + authors + ", klasse=" + klasse + ", group=" + group + '}';
    }

}
