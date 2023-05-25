package com.allert.allert.classes;

import java.util.ArrayList;
import java.util.List;

public class Need_Request extends Request{
    private Crisis crisis;

    public Crisis getCrisis() {
        return crisis;
    }

    public void setCrisis(Crisis crisis) {
        this.crisis = crisis;
    }
    public static List<Need_Request> needRequestList = new ArrayList<>();
    public Need_Request(User user, String description,Crisis crisis) {
        super(user,description);
        this.crisis = crisis;
        needRequestList.add(this);
    }
}
