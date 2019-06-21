package com.appstreet.airtelassignment.data.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

public class DevAssets implements Parcelable {

    /**
     * username : microsoft
     * type : organization
     * url : https://github.com/microsoft
     * avatar : https://avatars2.githubusercontent.com/u/6154722
     * repo : {"name":"malmo","description":"Project Malmo is a platform for Artificial Intelligence experimentation and research built on top of Minecraft. We aim to inspire a new generation of research into challenging new problems presented by this unique environment. --- For installation instructions, scroll down to *Getting Started* below, or visit the project page for more information:","url":"https://github.com/microsoft/malmo"}
     */

    private String username;
    private String type;
    private String url;
    private String avatar;
    private RepoBean repo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RepoBean getRepo() {
        return repo;
    }

    public void setRepo(RepoBean repo) {
        this.repo = repo;
    }

    public static class RepoBean implements Parcelable {
        /**
         * name : malmo
         * description : Project Malmo is a platform for Artificial Intelligence experimentation and research built on top of Minecraft. We aim to inspire a new generation of research into challenging new problems presented by this unique environment. --- For installation instructions, scroll down to *Getting Started* below, or visit the project page for more information:
         * url : https://github.com/microsoft/malmo
         */

        private String name;
        private String description;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.description);
            dest.writeString(this.url);
        }

        public RepoBean() {
        }

        protected RepoBean(Parcel in) {
            this.name = in.readString();
            this.description = in.readString();
            this.url = in.readString();
        }

        public static final Creator<RepoBean> CREATOR = new Creator<RepoBean>() {
            @Override
            public RepoBean createFromParcel(Parcel source) {
                return new RepoBean(source);
            }

            @Override
            public RepoBean[] newArray(int size) {
                return new RepoBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeString(this.avatar);
        dest.writeParcelable(this.repo, flags);
    }

    public DevAssets() {
    }

    protected DevAssets(Parcel in) {
        this.username = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
        this.repo = in.readParcelable(RepoBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DevAssets> CREATOR = new Parcelable.Creator<DevAssets>() {
        @Override
        public DevAssets createFromParcel(Parcel source) {
            return new DevAssets(source);
        }

        @Override
        public DevAssets[] newArray(int size) {
            return new DevAssets[size];
        }
    };
}
