package com.example.worldclock;

import android.util.Log;

public class WorldClock {
    public static double calculate_eot(int day) {
        /*
            calculate_eot(day) -> double

            `day` is the number of days since January 1 of the current year, the returned value is
            how much faster the sun is then our clock.

            For example, if the returned value is 5, the sun is 5 minutes faster than our clock, and
            the location where the sun is overhead is actually 5 minutes further west than we were
            expecting.
        * */

        // from wikipedia
        // en.wikipedia.org/wiki/Equation_of_time#Alternative_calculation

        // oof there's a bunch of degree -> conversion here. To prevent mistakes i've pretty much
        // copied this word-for-word from the wikipedia article.
        double W = 360 / (365.24);
        double A = W * (day + 10);
        double B = A + 360/Math.PI * 0.0167 * Math.sin(W * (day - 2) * Math.PI/180);
        double C = (A - 180/Math.PI * Math.atan2(Math.tan(B * Math.PI/180), Math.cos(23.44 * Math.PI/180))) / 180;

        double eot = 720 * (C - (int)(C + 0.5));
        return eot;
    }

    public static CharSequence get_location(int offset){
        /*
            get_location(offset) -> CharSequence

            offset is the offset in seconds from noon we are trying to get the location of, as an example, if
            we are trying to get the location of where it's 2AM, we would enter an offset of -10 * 3600
         */
        CharSequence[] cities_by_longitude = {"Egvekinot, Russia","Leava, Wallis And Futuna","Leava, Wallis And Futuna","Waitangi, New Zealand","Nuku‘alofa, Tonga","Neiafu, Tonga","Provideniya, Russia","Apia, Samoa","Pago Pago, American Samoa","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Uelen, Russia","Avarua, Cook Islands","Kapaa, United States","Honolulu, United States","Wailuku, United States","Kahului, United States","Hilo, United States","Hilo, United States","Hilo, United States","Kodiak, United States","Kalifornsky, United States","Papeete, French Polynesia","Anchorage, United States","Fairbanks, United States","Badger, United States","Badger, United States","Badger, United States","Badger, United States","Badger, United States","Badger, United States","Badger, United States","Badger, United States","Dawson, Canada","Dawson, Canada","Dawson, Canada","Dawson, Canada","Whitehorse, Canada","Juneau, United States","Tuktoyaktuk, Canada","Ketchikan, United States","Ketchikan, United States","Prince Rupert, Canada","Terrace, Canada","Port Hardy, Canada","Smithers, Canada","Burns Lake, Canada","Campbell River, Canada","Nanaimo, Canada","Vancouver, Canada","Seattle, United States","Sacramento, United States","Fresno, United States","Bakersfield, United States","Los Angeles, United States","San Diego, United States","Indio, United States","Las Vegas, United States","Calgary, Canada","Lethbridge, Canada","Phoenix, United States","Tucson, United States","Mazatán, Mexico","Los Mochis, Mexico","Guasave, Mexico","Culiacán, Mexico","Juárez, Mexico","Denver, United States","Gómez Palacio, Mexico","Guadalajara, Mexico","León de los Aldama, Mexico","San Luis Potosí, Mexico","Monterrey, Mexico","Mexico City, Mexico","Puebla, Mexico","Dallas, United States","Omaha, United States","Houston, United States","Des Moines, United States","Minneapolis, United States","Quetzaltenango, Guatemala","Guatemala City, Guatemala","St. Louis, United States","San Salvador, El Salvador","Chicago, United States","Nashville, United States","Indianapolis, United States","Cincinnati, United States","Atlanta, United States","Detroit, United States","Tampa, United States","Orlando, United States","Miami, United States","Toronto, Canada","Rochester, United States","Lima, Peru","Medellín, Colombia","Philadelphia, United States","New York, United States","Bucaramanga, Colombia","Maracaibo, Venezuela","Santiago, Chile","Santo Domingo, Dominican Republic","Barquisimeto, Venezuela","Valencia, Venezuela","Caracas, Venezuela","Cochabamba, Bolivia","San Miguel de Tucumán, Argentina","Córdoba, Argentina","Santa Cruz, Bolivia","Port of Spain, Trinidad And Tobago","Rosario, Argentina","Manaus, Brazil","Resistencia, Argentina","Buenos Aires, Argentina","Uruguaiana, Brazil","Montevideo, Uruguay","Campo Grande, Brazil","Santa Maria, Brazil","Cascavel, Brazil","Maringá, Brazil","Porto Alegre, Brazil","Ponta Grossa, Brazil","Curitiba, Brazil","Brasília, Brazil","São Paulo, Brazil","Santos, Brazil","Guaratinguetá, Brazil","Belo Horizonte, Brazil","Rio de Janeiro, Brazil","Cabo Frio, Brazil","Campos, Brazil","Vitória, Brazil","Fortaleza, Brazil","Salvador, Brazil","Aracaju, Brazil","Maceió, Brazil","Recife, Brazil","Recife, Brazil","Recife, Brazil","Recife, Brazil","Recife, Brazil","Recife, Brazil","Horta, Portugal","Horta, Portugal","Angra do Heroísmo, Portugal","Ponta Delgada, Portugal","Mindelo, Cabo Verde","Praia, Cabo Verde","Keflavík, Iceland","Reykjavík, Iceland","Selfoss, Iceland","Sauðárkrókur, Iceland","Sauðárkrókur, Iceland","Akureyri, Iceland","Dakar, Senegal","Nouakchott, Mauritania","Las Palmas de Gran Canaria, Spain","Conakry, Guinea","Freetown, Sierra Leone","Bo, Sierra Leone","Monrovia, Liberia","Agadir, Morocco","Lisbon, Portugal","Casablanca, Morocco","Rabat, Morocco","Sevilla, Spain","Fès, Morocco","Madrid, Spain","Bilbao, Spain","Birmingham, United Kingdom","Nottingham, United Kingdom","London, United Kingdom","Lomé, Togo","Paris, France","Lagos, Nigeria","Ibadan, Nigeria","Lyon, France","Geneva, Switzerland","Essen, Germany","Mannheim, Germany","Kano, Nigeria","Tunis, Tunisia","Florence, Italy","Rome, Italy","Luanda, Angola","Naples, Italy","Kinshasa, Congo (Kinshasa)","Vienna, Austria","Wrocław, Poland","Cape Town, South Africa","Katowice, Poland","Benghazi, Libya","Warsaw, Poland","Kananga, Congo (Kinshasa)","Sofia, Bulgaria","Athens, Greece","Helsinki, Finland","Bucharest, Romania","İzmir, Turkey","Johannesburg, South Africa","Istanbul, Turkey","Saint Petersburg, Russia","Cairo, Egypt","Omdurman, Sudan","Khartoum, Sudan","Lilongwe, Malawi","Tel Aviv-Yafo, Israel","Damascus, Syria","Nairobi, Kenya","Moscow, Russia","Addis Ababa, Ethiopia","Mecca, Saudi Arabia","Ivanovo, Russia","Ḩā’il, Saudi Arabia","Mosul, Iraq","Baghdad, Iraq","Yerevan, Armenia","Tabrīz, Iran","Riyadh, Saudi Arabia","Kuwait City, Kuwait","Kazan’, Russia","Baku, Azerbaijan","Tehran, Iran","Eşfahān, Iran","Shīrāz, Iran","Abu Dhabi, United Arab Emirates","Dubai, United Arab Emirates","Ufa, Russia","Kermān, Iran","Ashgabat, Turkmenistan","Muscat, Oman","Mashhad, Iran","Yekaterinburg, Russia","Herāt, Afghanistan","Turbat, Pakistan","Bukhara, Uzbekistan","Kurgan, Russia","Kandahār, Afghanistan","Karachi, Pakistan","Hyderabad City, Pakistan","Kabul, Afghanistan","Jalālābād, Afghanistan","Multan, Pakistan","Saidu Sharif, Pakistan","Mumbai, India","Lahore, Pakistan","Amritsar, India","Jaipur, India","Delhi, India","Bengalūru, India","Nāgpur, India","Chennai, India","Lucknow, India","Allahābād, India","Vishākhapatnam, India","Barnaul, Russia","Patna, India","Jamshedpur, India","Āsansol, India","Kolkata, India","Rājshāhi, Bangladesh","Dhaka, Bangladesh","Comilla, Bangladesh","Chittagong, Bangladesh","Krasnoyarsk, Russia","Kumul, China","Banda Aceh, Indonesia","Rangoon, Burma","Bago, Burma","Mawlamyine, Burma","Medan, Indonesia","George Town, Malaysia","Bangkok, Thailand","Kuala Lumpur, Malaysia","Kunming, China","Singapore, Singapore","Palembang, Indonesia","Hanoi, Vietnam","Jakarta, Indonesia","Bandung, Indonesia","Xi’an, China","Hechi, China","Gaozhou, China","Hohhot, China","Guangzhou, China","Shenzhen, China","Ganzhou, China","Beijing, China","Tianjin, China","Zhangzhou, China","Nanjing, China","Hangzhou, China","Shanghai, China","Taipei, Taiwan","Shenyang, China","Qiqihar, China","Changchun, China","Pyongyang, Korea, North","Seoul, Korea, South","Naha, Japan","Busan, Korea, South","Fukuoka, Japan","Kitaku, Japan","Hiroshima, Japan","Matsuyama, Japan","Okayama, Japan","Ōsaka, Japan","Kyōto, Japan","Nagoya, Japan","Hamamatsu, Japan","Adelaide, Australia","Tokyo, Japan","Sapporo, Japan","Asahikawa, Japan","Yuzhno-Sakhalinsk, Russia","Ashino, Japan","Melbourne, Australia","Cairns, Australia","Port Moresby, Papua New Guinea","Popondetta, Papua New Guinea","Canberra, Australia","Katoomba, Australia","Sydney, Australia","Newcastle, Australia","Brisbane, Australia","Tweed Heads, Australia","Sohano, Papua New Guinea","Oktyabr’skiy, Russia","Gizo, Solomon Islands","Palikir, Micronesia, Federated States Of","Petropavlovsk-Kamchatskiy, Russia","Honiara, Solomon Islands","Cherskiy, Russia","Ust’-Kamchatsk, Russia","Tofol, Micronesia, Federated States Of","Il’pyrskiy, Russia","Manily, Russia","Nouméa, New Caledonia","Luganville, Vanuatu","Invercargill, New Zealand","Macetown, New Zealand","Dunedin, New Zealand","Timaru, New Zealand","Ashton, New Zealand","Christchurch, New Zealand","New Plymouth, New Zealand","Auckland, New Zealand","Tauranga, New Zealand","Napier, New Zealand","Suva, Fiji","Labasa, Fiji","Labasa, Fiji"};

        long current_time = System.currentTimeMillis() / 1000L;
        long recorded_unix = 1598462917;
        int recorded_days_since_january = 238;

        // this is mainly just an approximation, but we can allow some pretty large errors for eot
        int days_since_january = (recorded_days_since_january + (int)((current_time - recorded_unix) / (3600 * 24))) % 365;

        double recorded_long = -122.1589;
        double recorded_lst = 7 * 15 + 41 * 15/60. + 41 * 15/3600.;
        double recorded_sun_ra = 10 * 15 + 22 * 15/60. + 13 * 15/3600.;

        double time_to_long_movement = 360 / 86400.002;

        double eot = calculate_eot(days_since_january);

        Log.d("EOT", Double.toString(eot));

        double recorded_position_sun = recorded_long + (recorded_sun_ra - recorded_lst) + time_to_long_movement * (offset - eot);
        double current_position_sun = recorded_position_sun - time_to_long_movement * (int)(current_time - recorded_unix);

        current_position_sun %= 360;
        if (current_position_sun < -180) {
            current_position_sun = 180 + (current_position_sun + 180);
        }else if (current_position_sun > 180) {
            current_position_sun = -180 + (current_position_sun - 180);
        }

        int idx = (int)Math.round(current_position_sun) + 179;

        return cities_by_longitude[idx];
    }


}
