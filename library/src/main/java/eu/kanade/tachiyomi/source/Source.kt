package eu.kanade.tachiyomi.source

import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import rx.Observable

/**
 * A basic interface for creating a source. It could be an online source, a local source, etc...
 */
@Suppress("unused")
interface Source {

    /**
     * Id for the source. Must be unique.
     */
    val id: Long

    /**
     * Name of the source.
     */
    val name: String

    /**
     * Returns an observable with the updated details for a manga.
     *
     * @param manga the manga to update.
     */
    fun fetchMangaDetails(manga: SManga): Observable<SManga>

    /**
     * Returns an observable with all the available chapters for a manga.
     *
     * @param manga the manga to update.
     */
    fun fetchChapterList(manga: SManga): Observable<List<SChapter>>

    /**
     * Returns an observable with the list of pages a chapter has.
     *
     * @param chapter the chapter.
     */
    fun fetchPageList(chapter: SChapter): Observable<List<Page>>

    // GBK -->
    /**
     * Resolves a URL to an [SManga] that this source can handle.
     *
     * Override this method to support importing manga from URLs (e.g. batch add).
     * Return an [SManga] with at least [SManga.url] set, or null if the URL
     * is not recognized.
     *
     * Default implementation returns null (not supported).
     *
     * @since goblock/extensions-lib 1.0
     * @param url the URL to resolve.
     * @return the resolved manga, or null.
     */
    suspend fun getMangaFromUrl(url: String): SManga? = null
    // GBK <--
}
