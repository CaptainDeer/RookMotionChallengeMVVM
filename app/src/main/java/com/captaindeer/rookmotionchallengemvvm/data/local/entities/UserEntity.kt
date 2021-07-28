package com.captaindeer.rookmotionchallengemvvm.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Entity(tableName = "users")
@Parcelize
class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "first_name") val first_name: String,
    @ColumnInfo(name = "last_name") val last_name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "avatar") val avatar: String
): Parcelable