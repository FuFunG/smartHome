# smartHome

## Planing

* Listview (Rooms, Fixtures)
* Architecture (Activity, Adapter, AsyncTask, Model, PreferencesUtils, Constant)
* Weather API Periodically
* SwitchButton

## Problems

* API rooms is not a array
* control onchange position of fixture
* how to handle Weather API Periodically

## Solutions

* Loop JSONObject to get all the key
```
JSONObject rooms = new JSONObject(json).getJSONObject("rooms");
Iterator<String> keys = rooms.keys();

while(keys.hasNext()) {
    String key = keys.next();
    if (rooms.get(key) instanceof JSONObject) {
        Log.i("room", key);
        JSONObject room = (JSONObject) rooms.get(key);
        JSONArray roomFixtures = room.getJSONArray("fixtures");
        response.rooms.add(setFixtures(key, roomFixtures));
    }
}
```
* used Holder to handle getting the position of fixtures
* trying to use AlarmManager

## Remaining Tasks

* Deployment (Heroku)
* Weather API Periodically

## My Technical Choices

* Created a Room.class to structure the API
* Shared Preferences will store the RoomModel state and update when user switch any fixtures
* Created a asyncTask that allow passing a callback function with API call response


