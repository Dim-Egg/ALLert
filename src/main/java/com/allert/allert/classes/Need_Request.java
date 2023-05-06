package com.allert.allert.classes;

public class Need_Request extends Request{
    private Crisis crisis;

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }

    public Need_Request(User user, String description,Crisis crisis) {
        super(user,description);
        this.crisis = crisis;
    }
}
