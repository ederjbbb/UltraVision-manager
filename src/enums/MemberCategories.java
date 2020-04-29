package enums;

public enum MemberCategories {

        MUSIC("Music"),
        LIVE_C_VIDEOS("Live Concerts"),
        MOVIES("Movies"),
        BOX_SET("Box Set");

        String value;
        MemberCategories(String value) {
                this.value = value;
        }

        public String getValue() {
                return value;
        }
}


