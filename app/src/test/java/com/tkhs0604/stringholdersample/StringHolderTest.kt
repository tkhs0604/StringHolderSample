package com.tkhs0604.stringholdersample

import android.content.Context
import android.os.Parcel
import androidx.core.os.bundleOf
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class StringHolderTest {

    lateinit var appContext: Context

    @Before
    fun setUp() {
        appContext = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `equality for plain value`() {
        assertEquals(
            StringHolder.Plain("test"),
            StringHolder.Plain("test")
        )
    }

    @Test
    fun `equality for resource value`() {
        assertEquals(
            StringHolder.Resource(R.string.app_name),
            StringHolder.Resource(R.string.app_name)
        )
    }

    @Test
    fun `equality for resource value with args`() {
        assertEquals(
            StringHolder.Resource(R.string.app_copyright, "2021", "tkhs0604"),
            StringHolder.Resource(R.string.app_copyright, "2021", "tkhs0604")
        )
    }

    @Test
    fun `getString() for plain type`() {
        appContext = ApplicationProvider.getApplicationContext()
        assertEquals(
            "test",
            StringHolder.Plain("test").getString(appContext)
        )
    }

    @Test
    fun `getString() for resource type`() {
        appContext = ApplicationProvider.getApplicationContext()
        assertEquals(
            "StringHolderSample",
            StringHolder.Resource(R.string.app_name).getString(appContext)
        )
    }

    @Test
    fun `getString() for resource type with args`() {
        appContext = ApplicationProvider.getApplicationContext()
        assertEquals(
            "Copyright 2021 tkhs0604",
            StringHolder.Resource(R.string.app_copyright, "2021", "tkhs0604").getString(appContext)
        )
    }

    @Test
    fun `marshalling and unmarshalling for plain type`() {
        val key = "key"
        val parcel = Parcel.obtain()

        val sut = StringHolder.Plain("test")

        // marshalling
        parcel.writeBundle(bundleOf(key to sut))

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0)

        // unmarshalling
        val bundle = parcel.readBundle(sut.javaClass.classLoader)
        val actual = bundle?.getParcelable<StringHolder>(key)?.getString(appContext)
        assertEquals("test", actual)
    }

    @Test
    fun `marshalling and unmarshalling for resource type`() {
        val key = "key"
        val parcel = Parcel.obtain()

        val sut = StringHolder.Resource(R.string.app_name)

        // marshalling
        parcel.writeBundle(bundleOf(key to sut))

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0)

        // unmarshalling
        val bundle = parcel.readBundle(sut.javaClass.classLoader)
        val actual = bundle?.getParcelable<StringHolder>(key)?.getString(appContext)
        assertEquals("StringHolderSample", actual)
    }

    @Test
    fun `marshalling and unmarshalling for resource type with args`() {
        val key = "key"
        val parcel = Parcel.obtain()

        val sut = StringHolder.Resource(R.string.app_copyright, "2021", "tkhs0604")

        // marshalling
        parcel.writeBundle(bundleOf(key to sut))

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0)

        // unmarshalling
        val bundle = parcel.readBundle(sut.javaClass.classLoader)
        val actual = bundle?.getParcelable<StringHolder>(key)?.getString(appContext)
        assertEquals("Copyright 2021 tkhs0604", actual)
    }
}
