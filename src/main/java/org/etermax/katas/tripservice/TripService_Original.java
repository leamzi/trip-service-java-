package org.etermax.katas.tripservice;

import org.etermax.katas.tripservice.exception.UserNotLoggedInException;
import org.etermax.katas.tripservice.trip.Trip;
import org.etermax.katas.tripservice.trip.TripDAO;
import org.etermax.katas.tripservice.user.User;
import org.etermax.katas.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService_Original {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = TripDAO.findTripsByUser(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

}
