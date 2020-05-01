package enums;

public enum MemberCategories {

        MUSIC("Music Lovers"),// can rent CDs Live video Concerts
        LIVE_C_VIDEOS("Video Lovers"),// can rent van only rent movies
        MOVIES("TV Lovers"),// can only
        BOX_SET("Premium");// can rent anything

        String value;
        MemberCategories(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}


