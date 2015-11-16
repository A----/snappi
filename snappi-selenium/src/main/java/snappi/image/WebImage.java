package snappi.image;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Extends {@link Image} to better handle {@link WebElement}s.
 */
public class WebImage extends Image {
  
  /**
   * If rendering only an element and not the whole page, this point is the top-left
   * corner of that element.
   * That element has to be a parent of {@link #searchContext} or {@link IllegalArgumentException}
   * and {@link ArrayIndexOutOfBoundsException} will occur.
   * Is used to compute relative position of ignored elements.
   */
  private Point relativeOffset;
  
  /**
   * Lots of {@code ignoreElements(…)} methods are searched from this context.
   * Can be a {@link WebDriver} or {@link WebElement}. In the latter, it is common to set {@link #relativeOffset} from
   * its {@link WebElement#getLocation()}.
   * If unset, it will cause {@link IllegalStateException} when calling {@code ignoreElements(…)}.
   * @see #setSearchContext(SearchContext)
   */
  private SearchContext searchContext;
  
  /**
   * Initiliazes the object with a simple image.
   * Relative offset is (0, 0).
   * @param image Said image.
   */
  public WebImage(BufferedImage image) {
    this(image, new Point(0, 0));
  }
  
  /**
   * Initiliazes the object with an image and relative offset.
   * @param image Said image.
   * @param relativeOffset Said offset.
   */
  public WebImage(BufferedImage image, Point relativeOffset) {
    super(image);
    this.relativeOffset = relativeOffset;
  }
  
  /**
   * Initializes the object with an image.
   * The relative offset is computed from the {@code relativeElement} argument.
   * @param image Said image.
   * @param relativeElement Said element.
   */
  public WebImage(BufferedImage image, WebElement relativeElement) {
    super(image);
    this.relativeOffset = relativeElement.getLocation();
  }
  
  /**
   * Retrieves the current search context.
   * @return The current search context.
   */
  public SearchContext getSearchContext() {
    return this.searchContext;
  }

  /**
   * Sets the current search context.
   * @param driverOrElement New search context. 
   * @return Itself.
   * @see #searchContext
   */
  public WebImage setSearchContext(SearchContext driverOrElement) {
    this.searchContext = driverOrElement;
    return this;
  }

  /**
   * {@inheritDoc}
   * Same as inherited but return value has the right type.
   */
  @Override
  public WebImage ignoreRegion(int x, int y, int w, int h) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    super.ignoreRegion(x, y, w, h);
    return this;
  }
  
  /**
   * Ignores one or more HTML elements.
   * @param elements One or more elements.
   * @return Itself.
   * @throws ArrayIndexOutOfBoundsException If the element was outside of the snapped image.
   */
  public WebImage ignoreElement(WebElement... elements) {
    for(WebElement element: elements) {
      Point location = element.getLocation();
      Dimension size = element.getSize();
      
      this.ignoreRegion(location.x - relativeOffset.x, location.y - relativeOffset.y, size.width, size.height);
    }
    
    return this; 
  }
  
  /**
   * Ignores one or more HTML elements.
   * @param elements One or more elements.
   * @return Itself.
   * @throws ArrayIndexOutOfBoundsException If the element was outside of the snapped image.
   */
  public WebImage ignoreElements(Collection<WebElement> elements) {
    final WebElement[] emptyWebElementArray = {};
    
    ignoreElement(elements.toArray(emptyWebElementArray));
    
    return this;
  }
  
  /**
   * Ignore one and only one HTML element.
   * @param by Custom Selenium selector. Relative to the {@link #searchContext}.
   * @return Itself.
   * @throws IllegalStateException If no search context is defined. See {@link #setSearchContext(SearchContext)}.
   * @throws NoSuchElementException If no element could be found with the {@code by} parameter.
   * @throws ArrayIndexOutOfBoundsException If the element was outside of the snapped image.
   * @see #ignoreElements(By...)
   */
  public WebImage ignoreElement(By by) throws IllegalStateException, NoSuchElementException {
    if (this.searchContext == null) {
      throw new IllegalStateException("Search context has not been set.");
    }
    
    WebElement element = this.searchContext.findElement(by);
    this.ignoreElement(element);
    
    return this;
  }
  
  /**
   * Ignores one or more HTML elements.
   * @param bys One or more custom Selenium selectors. Relative to the {@link #searchContext}.
   * @return Itself.
   * @throws IllegalStateException If no search context is defined. See {@link #setSearchContext(SearchContext)}.
   * @throws ArrayIndexOutOfBoundsException If the element was outside of the snapped image.
   */
  public WebImage ignoreElements(By... bys) throws IllegalStateException {
    if (this.searchContext == null) {
      throw new IllegalStateException("Search context has not been set.");
    }
    
    for(By by: bys) {
      List<WebElement> elements = this.searchContext.findElements(by);
      this.ignoreElements(elements);
    }
    
    return this;
  }
}
